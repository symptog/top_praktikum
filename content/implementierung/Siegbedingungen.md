## Sterben und Siegen

### Sterbeanimation und Auswirkungen des Sterbens

Was passiert, wenn ein Spieler von einer Bombenexplosion erwischt wird?  Einfach die Position des Bomberman wieder auf Ausgangsposition zu setzen, reicht nicht aus. Es muss erkennbar sein, dass die Spielfigur gerade gestorben ist. Dazu wurde eine Verzögerung von 300 Frames eingebaut, bevor die Position zurückgesetzt wird. Während diesen 300 Frames wird die Steuerung deaktiviert, und eine Animation abgespielt. Diese Sterbeanimation sollte wieder so simpel wie möglich sein. Der erste Entwurf enthielt 2 Bilder, die wieder abwechselnd angezeigt werden sollten: Die Frontalansicht des Bomberman (statt den normalen Augen wurden Kreuze angezeigt), und das gleiche Bild mit einer weißen Umrandung. Dies sollte sowohl das Sterben verdeutlichen, als auch anzeigen, dass die Figur aktuell unverwundbar ist. Bei einem ersten Test wurde dann allerdings festgestellt, dass eine weiße Umrandung auf weißem Hintergrund nur schwer erkennbar ist. Die Umrandung wurde daraufhin in schwarz umgeändert.
Sollte ein Bomberman sterben und keine weiteren Leben mehr besitzen, wird nach den 300 Frames die Spielfigur nicht mehr angezeigt, die Steuerung deaktiviert und die Steuerfläche mit dem Hinweis versehen, dass der Spieler leider verloren hat.

### Siegbedingungen

Die erste Version der Siegbedingungen entstand recht kurzfristig vor dem ersten Usertest.
Die Variante war daher noch nicht wirklich gut durchdacht, funktionierte allerdings erst
einmal. In der Field Klasse wurde eine neue Funktion angelegt, die abprüft, ob nur noch ein
Spieler von den 4 Spielern übrig ist. Hierzu wurden für die Bomberman Klasse zwei neue
Attribute festgelegt: playing und alive. Beide Werte sind standardmäßig false, und werden
erst bei Spieleinstieg durch den Konstruktor auf true gesetzt. Das Attribut alive sollte dazu
dienen, um festzustellen, ob der Spieler noch aktiv am Spiel teilnimmt. Sobald der
Bomberman aktiviert wurde, wurde der Wert auf true gesetzt, und sobald der Spieler sein
letztes Leben verloren hatte, wurde der Wert wieder auf false gesetzt. Das Attribut playing
wurde nach dem Setzen auf true durch den Konstruktor dauerhaft auf true gelassen, um
ermitteln zu können, ob der Spieler bzw. Bomberman irgendwann am Spiel teilgenommen
hat. Wenn also sowohl alive als auch playing false sind, ist der Bomberman noch nicht
aktiviert worden. Sind beide Variablen true, ist der Bomberman aktiv und noch im Spiel. Ist
alive false, und playing true, hat der Bomberman am Spiel teilgenommen, ist inzwischen
aber bereits ausgeschieden.
In der ersten Version wurde die Funktion für die Siegbedingung alle 60 Frames aufgerufen.
Die Funktion hat dann überprüft, ob alle 4 Spieler am Spiel teilgenommen haben (bei allen
musste playing true sein), und ob nur noch ein Spieler am Leben ist (bei 3 Spielern alive auf
false).
Als schnelle Notlösung war diese Version in Ordnung, hatte jedoch noch einige Probleme: Es
konnte nur ein Sieger gefunden werden, wenn 4 Spieler teilnahmen. Starben die letzten zwei
Spieler zudem innerhalb des 60 Frames Intervalles, gab es keinen Sieger.
Einer der Tester machte den Vorschlag, die Funktion nur aufzurufen, wenn ein Spieler
ausscheidet. Dieser Vorschlag wurde dann auch umgesetzt.
Die Änderung erleichterte die Abfrage, und ermöglichte es zudem auch, einen Sieger zu
ermitteln, wenn nur 2 oder 3 Spieler gespielt haben. Wenn ein Spieler ausscheidet, wird sein
alive Attribut wieder auf false gesetzt. Wenn jetzt nur noch ein weiterer Spieler am Leben ist,
wird er zum Sieger ernannt. Das playing Attribut wurde trotzdem weiterhin benutzt, und
dadurch konnte ermittelt werden, ob die Spieler einfach nur nacheinander allein gespielthaben. Wenn bei allen Spielern playing auf true und alive auf false gesetzt war (was nur
passieren kann, wenn ein Spieler allein spielt und sich selbst oft genug tötet, bevor der
nächste Spieler dazu stößt), kommt statt einer Siegmeldung der Hinweis, dass man doch
bitte gemeinsam, und nicht nacheinander spielen soll.
