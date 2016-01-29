\begin{figure}[h]
    \centering
    \caption{Screenshot des Bomberman-Spiels}
    \includegraphics[width=\textwidth]{figures/screenshot01.png}
    \label{fig:screenshot01}
\end{figure}

## Sterben und Siegen

### Sterbeanimation und Auswirkungen des Sterbens

Was muss alles passieren, wenn ein Spieler sich in einer Bombenexplosion befindet?  Einfach die Position des Bomberman wieder auf Ausgangsposition zu setzen, reicht nicht aus. Es muss erkennbar sein, dass die Spielfigur gerade gestorben ist. Dazu wurde eine Verzögerung von 300 Frames eingebaut, bevor die Position zurückgesetzt wird. Während diesen 300 Frames wird die Steuerung deaktiviert und eine Animation abgespielt. Diese Sterbeanimation muss wieder so simpel wie möglich sein. Der erste Entwurf enthielt zwei Bilder, die erneut abwechselnd gezeigt werden: Die Frontalansicht des Bomberman (statt den normalen Augen werden Kreuze angezeigt) und das gleiche Bild mit einer weißen Umrandung. Dies verdeutlicht sowohl das Sterben, als auch, dass die Figur aktuell unverwundbar ist. Bei einem ersten Test wurde dann allerdings festgestellt, dass eine weiße Umrandung auf weißem Hintergrund nur schwer erkennbar ist. Die Umrandung wurde daraufhin in schwarz umgeändert.
Sollte ein Bomberman sterben und keine weiteren Leben mehr besitzen, wird nach den 300 Frames die Spielfigur nicht mehr angezeigt, die Steuerung deaktiviert und die Steuerfläche mit dem Hinweis versehen, dass der Spieler verloren hat.

### Siegbedingungen

In der `Field`-Klasse wurde eine neue Funktion angelegt, die abprüft, ob nur noch ein
Spieler von den vier Spielern übrig ist. Hierzu wurden für die `Bomberman`-Klasse zwei neue
Attribute festgelegt: "playing" und "alive". Beide Werte sind standardmäßig false und werden
erst bei Spieleinstieg durch den Konstruktor auf true gesetzt. Das Attribut "alive" wird genutzt, um festzustellen, ob der Spieler noch aktiv am Spiel teilnimmt. Sobald der
Bomberman aktiv ist, wird der Wert auf true gesetzt und sobald der Spieler sein
letztes Leben verliert, wird der Wert wieder auf false gesetzt. Das Attribut "playing"
wird nach dem Setzen auf true durch den Konstruktor dauerhaft so belassen, um
ermitteln zu können, ob der Spieler bzw. Bomberman irgendwann einmal am Spiel teilgenommen
hat. Wenn also sowohl "alive", als auch "playing" false sind, ist der Bomberman noch nicht
aktiviert worden. Sind beide Variablen true, so ist der Bomberman aktiv und noch im Spiel. Ist
"alive" false und "playing" auf true, hat der Bomberman am Spiel teilgenommen, ist inzwischen
aber bereits ausgeschieden.
In der ersten Version wurde die Funktion für die Siegbedingung aller 60 Frames aufgerufen.
Die Funktion hat dann überprüft, ob alle vier Spieler am Spiel teilgenommen haben (bei allen
musste "playing" true sein) und ob nur noch ein Spieler am Leben ist (bei drei Spielern "alive" auf
false).
Als Notlösung war diese Version in Ordnung, hatte jedoch noch einige Probleme: So
konnte beispielsweise nur ein Sieger gefunden werden, wenn alle Spieler teilnahmen. Starben die letzten Beiden zudem innerhalb von 60 Frames, gab es keinen Sieger.
Einer der Tester machte den Vorschlag, die Funktion nur aufzurufen, wenn ein Spieler
ausscheidet. Dieser Einwurf war hilfreich und wurde dann auch umgesetzt.
Die Änderung erleichterte die Abfrage und ermöglichte es zudem auch, einen Sieger zu
ermitteln, wenn nur zwei oder drei Spieler gespielt haben. Wenn ein Spieler nun ausscheidet, wird sein
"alive"-Attribut wieder auf false gesetzt. Wenn nur noch ein weiterer Spieler am Leben ist,
wird er automatisch zum Sieger ernannt. Das "playing"-Attribut wurde trotzdem weiterhin benutzt und
dadurch konnte ermittelt werden, ob die Spieler einfach nur nacheinander allein gespielt haben. Wenn bei allen Spielern "playing" auf true und "alive" auf false gesetzt ist (was nur
passieren kann, wenn ein Spieler allein spielt und sich selbst oft genug tötet, bevor der
nächste Spieler dazu stößt), kommt statt einer Siegermeldung der Hinweis, dass man doch
bitte gemeinsam und nicht nacheinander spielen soll.
