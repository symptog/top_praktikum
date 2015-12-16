# Bewegungsanimation
### Was war bereits vorhanden?
Als die Arbeit an der Bewegungsanimation begann, war bereits das Grundgerüst vorhanden.
Es gab also schon verschiedene Blöcke auf dem Spielfeld, ebenso waren bereits Abfragen
eingebunden, um zu ermitteln, welche Blöcke von einem Bomberman besucht werden
können. Diese Abfragen waren in Funktionen eingegliedert, namentlich moveright(),
moveleft(), moveup() und movedown(). Sie sollten ausgeführt werden, wenn man den
jeweiligen Richtungsbefehl erhält. Da zu diesem Zeitpunkt allerdings noch keine
Bewegungssignale von den Touchzones übermittelt wurden, wurde zu Testzwecken ein
Bomberman jeden Frame in die gleiche Richtung laufen gelassen. Zudem gab es bereits
einen Bomberman als Platzhalter, der sich ohne jede Animation mittels der oben genannten
move Funktionen über das Spielfeld bewegen lässt.

### Grundidee
Die Animation sollte insgesamt eher minimalistisch werden, jedoch trotzdem einen gewissen
Charme versprühen. Damit man überhaupt eine Animation erkennen kann, braucht man
mindestens zwei verschiedene Bilder, die abwechselnd angezeigt werden. Es wurden also für
jede Bewegungsrichtung zwei verschiedene Bilder (mehr dazu im nächsten Absatz „Bilder
Entstehung“) entworfen, die in einem bestimmten Takt abwechselnd angezeigt werden
sollten, und zusätzlich ein Bild für den Ruhezustand, wenn die Figur gerade nicht bewegt
wird.
Da auch geplant war, ein Upgrade einzubinden, was die Figur schneller laufen lässt, musste
sowohl die Animation als auch die interne Positionsbestimmung von der aktuellen
Geschwindigkeit der Figur abhängig gemacht werden.

### Bilder Entstehung
Als Grundlage für den Entwurf der Animationsbilder wurde der Platzhalter genommen: ein
einzelner Bomberman in der Frontalansicht, ohne erkennbare Beine. Eine
Bewegungsanimation kann man jedoch am ehesten an einer Bewegung der Beine erkennen.
Der erste Schritt war also, dem Bomberman Beine zu verpassen. Dazu wurde einfach der
„Rock“, den unser Platzhalter Bomberman an hat, etwas angepasst, und das Resultat war mit
etwas Toleranz durchaus als Beine erkennbar. Dieses Bild (Blickrichtung unten, beide Beine
am Boden) wurde dann das Bild für den Ruhezustand.
Da der Bomberman quasi „nach unten“ schaut, wurden zuerst die Animationsbilder für die
movedown() Funktion entworfen. Hierzu wurde wieder unser Ruhezustand Bild genommen,
und daraus zwei neue Bilder entwickelt: auf einem Bild ist der linke Fuß angehoben, und auf
dem anderen Bild der rechte Fuß. Wenn die Bilder abwechselnd angezeigt werden, war
bereits einen simpler Bewegungsablauf erkennbar.
Als nächstes wurden die Bilder für die moveup() Funktion entworfen, also die Bewegung
nach oben. Hierzu wurde das Ruhezustand Bild einfach horizontal gespiegelt, und die Augen
und der Mund des Bomberman entfernt. Anschließend sind wieder zwei Versionen
entstanden, mit jeweils einem Bein angehoben.Für die seitlichen Bewegungen (also moveleft() und moveright()) mussten einige
Anpassungen vorgenommen werden: Der Docht, der aus dem Kopf des Bomberman ragt,
musste entsprechend positioniert werden, ebenso wurde jeweils nur ein Auge verwendet,
welches in Laufrichtung blickt. Der ursprüngliche Platzhalter, der als Vorlage benutzt wurde,
hatte jedoch nur einen linken Arm, mit dem er eine Bombe hält. Wenn sich der Bomberman
nach rechts bewegt, konnte man also keine Arme sehen, da der linke Arm vom Kopf verdeckt
wurde. Bei den Bildern für moveright() wurde dann kurzerhand ein kleiner verstümmelter
Arm eingefügt, als kleiner makabrer Hinweis, dass Bomben kein Spielzeug sind.
Auflösungsbedingt war der verstümmelte Arm aber kaum erkennbar auf dem Spielfeld. Die
Beine der Bomberman wurden für die seitwärts Bewegungen kurzerhand aus zwei Dreiecken
entworfen, die einmal geschlossen, und einmal geöffnet waren. So war auch bei der
Seitwärtsbewegung eine Bewegungsanimation erkennbar.
Da jetzt alle 9 nötigen Bilder vorhanden waren, wurden die Bilder auch noch für die Farben
der anderen 3 Bomberman erstellt. Hier tauchte aber recht schnell ein Problem auf: wenn in
Photoshop die Bereiche einfach mittels Farbeimer eingefärbt wurden, wurden die
Übergänge zum transparenten Hintergrund nicht passend umgefärbt, und es konnten bei
den Bildern Treppenkanten erkannt werden. Die Bomberman wirkten also grob pixelig.
Dieses Problem wurde jedoch recht schnell mittels der Farbwertkorrektur gelöst, allerdings
gab es jetzt leichte Abweichungen zu den ursprünglich gewählten Farben. Die neuen Farben
wurden beibehalten.

### Umsetzung
Die benötigten Bilder waren alle erstellt, und die Grundidee zum Ablauf stand auch. Es
konnte also damit begonnen, die Animation im Quellcode einzubinden.
Grundsätzlich sollte die Berechnung der Position getrennt von der Animation stattfinden.
Daher wurden die move Funktionen nur geringfügig angepasst. Eines der Attribute der
Bomberman beinhaltet die aktuelle Geschwindigkeit (Attribut „speed“) des jeweiligen
Bomberman, und entspricht der Anzahl Pixel, die sich der Bomberman bei einem move
Befehl pro Frame in die gewünschte Richtung bewegen soll. Die Veränderung der Position in
den move Funktionen wurde also statt von einem festen Wert einfach von dem
Geschwindigkeits Attribut abhängig gemacht.
Die Bomberman haben ihre eigene draw Funktion, in der bestimmt wird, was auf dem
Bildschirm ausgegeben werden soll.
Vom Touchpanel sollte immer eines von 5 verschiedenen Signalen kommen: direction=0 für
stehend, direction=10 für Bewegung nach oben, direction=20 für Bewegung nach unten,
direction=30 für Bewegung nach links und direction=40 für Bewegung nach rechts.
In der draw-Funktion unserer Bomberman wurden also abhängig von der direction
unterschiedliche Bilder ausgegeben. Bevor die Bilder gezeichnet wurden, wurden sie
einmalig gerendert und in einer Hashmap gespeichert.
Wenn die direction 0 war, der Bomberman also einfach nur da stand, wurde einfach nur das
Bild für den stehenden Bomberman gezeichnet.Wenn der Bomberman sich allerdings in eine bestimmte Richtung bewegen sollte, mussten
in einem bestimmten Takt zwei verschiedene Bilder auf dem Bildschirm gezeichnet werden.
Um dies zu realisieren, wurde in der Bomberman Klasse ein weiteres Attribut namens
„count“ angelegt. Count ist ein float Wert, und soll Werte zwischen 0 und 50 annehmen.
Wenn count kleiner als 25 ist, wird Bild 1 der entsprechenden Richtung gezeichnet, wenn
count größer gleich 25 ist, wird Bild 2 gezeichnet. In beiden Fällen wird zudem der
entsprechende move-Befehl ausgeführt. Wenn count größer gleich 50 war, wurde count
wieder auf 0 resettet.
In der ersten Version zählte count einfach bei jedem Aufruf um 1 nach oben. Damit sich bei
höherem speed (nach einsammeln der entsprechenden Upgrades) die
Bewegungsgeschwindigkeit auch optisch erhöht, wurde count ab der zweiten Version immer
um den aktuellen speed Wert erhöht. Auf diese Weise verkürzt sich das Intervall, in dem sich
die Bilder abwechseln.


### Erste Tests
Beim ersten Nutzertest wurde die Bewegungsanimation als angenehm bewertet, es gab
insgesamt keine weiteren Auffälligkeiten in diesem Bereich.

### Nachteile/Probleme
Da die Animation komplett abhängig von den Frames ist, (also der fps (frames per second))
statt von der vergangenen Zeit, wirkt die Bewegung bei niedriger fps (bedingt durch z.B. zu
langsamer Rechner für die gewählte Auflösung) deutlich langsamer, als bei höherer fps.
Dieses Problem könnte behoben werden, indem eine Variante entwickelt wird, die nur von
der Zeit abhängig ist.


# Entwicklung der Bomben & deren Animation
### Was war bereits vorhanden?
In dem Grundgerüst war es bereits möglich, den einzelnen Feldelementen verschiedene
Zustände zuzuweisen: „STATIC“ für einen nicht wegsprengbaren Block, „BLOCK“ für einen
sprengbaren Block, „EMPTY“ für ein leeres, begehbares Feld, und es gab schon einen ersten
Platzhalter für die späteren Upgrades. Zudem konnte man den einzelnen Feldelementen den
Zustand „BOMBE“ zuweisen.

### Grundidee/Umsetzung
Da die Programmierung der Bomben voraussichtlich recht umfangreich werden würde,
wurde entschieden, eine extra Klasse zu erstellen, statt es mit in die Bomberman Klasse
einzubinden. Ein Objekt der Klasse sollte immer genau eine Bombe darstellen, und alle damit
verbundenen Funktionalitäten (runterticken des Countdowns, danach alles im
Explosionsradius zerstören, bei weiteren Bomben diese sofort auslösen etc.) erfüllen.
Die nächste Frage war, wie es jetzt möglich war, dynamisch Objekte dieser Klasse erstellen
zu können. Da in dieser Richtung kein wirklich schöner Lösungsansatz gefunden wurde,
wurde zu Spielbeginn eine festgelegte Anzahl Bombenobjekte erzeugt, und diese dann
immer wieder zu benutzt.Damit es auf dem Spielfeld nicht zu unübersichtlich wird, wurde festgelegt, dass ein Spieler
maximal 5 Bomben gleichzeitig ablegen kann. Es wurde also ein Feldbenötigt, welches 20
Objekte der Bombenklasse verwalten kann. Dieses Feld wurde in der field- Klasse erstellt.
Jeder Bomberman hatte 5 festgelegte Feldelemente, die seine Bomben abspeicherten (z.B.
Spieler rot die Feldelemente 0-4). Jede Bombe hatte Attribute für ihre Position, ob sie aktuell
genutzt wird (also ob sie auf dem Spielfeld liegt), ihre Reichweite bei der Explosion, und die
aktuelle Zeit, bis wann sie explodiert.
Wenn vom Touchpanel der Befehl zum Legen einer Bombe kam, wurde zuerst überprüft, ob
der Spieler noch eine Bombe verfügbar hat. Danach wurde in dem Bombenfeld im
entsprechenden Intervall (bei Spieler rot z.B. 0-4) nach einer ungenutzten Bombe gesucht,
und diese bekam die aktuelle Position des Spielers zugewiesen, wurde als genutzt markiert,
und der Countdown wurde initialisiert.

### Bombenanimation
Die Animation der Bomben sollte wieder recht minimalistisch werden, jedoch sollte es
möglich sein, auf den ersten Blick grob abschätzen zu können, wie lang die Bombe ungefähr
noch benötigt, bis sie explodiert. Die Bombe sollte ungefähr 3 Sekunden (also 180 Frames
bei 60 fps) benötigen, bis sie selbstständig explodiert. Es sollte eine entsprechende Zahl auf
der Bombe erkennbar sein (die 3 in grün, die 2 in gelb, die 1 in rot), die anzeigt, wie lange es
noch dauert. Zudem sollte noch eine kleine Animation eingebunden werden, um zu
erkennen, dass die Bombe aktiv ist.
Hierzu wurden 7 verschiedene Bilder entworfen:
Zwei Bilder mit einer kleinen Bombe, die mit einer grünen 3 in der Mitte versehen sind, und
sich nur durch ihre Farbe am Dochtende unterscheiden (bei einem Bild war die Flamme am
Dochtende innen rot und außen gelb, bei dem anderen Bild war es umgedreht). Diese beiden
Bilder sollen sich in der ersten Sekunde in einem angebrachten Takt abwechseln.
Für die zweite Sekunde wurden 3 Bilder entwickelt. Die ersten 2 Bilder waren wie die Bilder
für die erste Sekunde, statt der grünen 3 war diesmal allerdings eine gelbe 2 auf den
Bomben zu sehen. Für das dritte Bild wurde die Bombe dann deutlich vergrößert. Die Idee
dahinter war, dass sich in den ersten 1 1/2 Sekunden nur die Flamme des Dochtes und die Zahl
ändert, und in den zweiten 1 1/2 Sekunden des Countdowns sich zusätzlich noch die Größe
der Bombe verändert, um zu verdeutlichen, dass sie gleich explodiert.
Für die dritte Sekunde entstanden 2 Bilder: eine kleine Bombe mit einer roten 1, und eine
große Bombe mit ebenfalls einer roten 1. Diese beiden Bilder sollten dann in einem
schnellen Intervall abwechselnd angezeigt werden.
Im Quellcode wurde die Animation dann recht simpel eingebunden: Die Bilder wurden
wieder einmalig gerendert und in einer Hashmap gespeichert. In jedem Frame wurde der
Countdown um 1 verringert, und alle 10 Frames wurde ein anderes Bild angezeigt, nach
oben beschriebenem Muster.Zudem enstand für die Bomben noch ein weiteres Bild. Eine Flamme, die die Zerstörung bzw.
deren Ausbreitung darstellen soll.


### Algorithmus zum Wegsprengen der Blöcke/Bomberman
Nach Ablauf des Countdowns sollte die Bombe explodieren und alles in ihrem Radius
zerstören. Eine der ersten Designentscheidungen, die bezüglich der Bomben getroffen
wurde, war, dass nur der erste Block in den jeweiligen Richtungen weggesprengt werden
soll, und nicht mehrere Blöcke hintereinander. Die Explosion sollte sich bei „freier Bahn“
(kein BLOCK/ STATIC/coveredItem/Feldende in den 4 Richtungen in der Reichweite)
komplett bis zu dem Radius entfalten, ansonsten entsprechend früher stoppen. Die
Flammen der Explosion sollten ungefähr 1 Sekunde bestehen bleiben, also 60 Frames.
Es wurde also einen Algorithmus entworfen, der mittels einer Schleife in jede der 4
Richtungen (+ natürlich den Koordinaten, an dem die Bombe vorher lag) folgendes abprüft:
Bis die Explosion auf ein Hindernis stößt (STATIC/BLOCK/coveredItem/Feldende) wird in
jedem Feldelement eine Flamme angezeigt, und es wird abgeprüft, ob sich ein Bomberman
oder eine Bombe auf eben diesem Feld befindet. Wenn sich ein Bomberman auf diesem Feld
befindet, wird dessen „startDie“ Methode aufgerufen, und für eine Bombe wird auf diese
Koordinaten die „bombexplode“ Methode ausgeführt. Auf die „startDie“ Methode wird im
Kapitel Sterbeanimation genauer eingegangen. Da die Bomben nicht direkt mit dem Feld
verknüpft sind (es wird lediglich vermerkt, dass eine Bombe an der entsprechenden Stelle
liegt, aber nicht welche), wird in der „bombexplode“ Methode das Bombenfeld nach den
passenden Koordinaten durchsucht, und deren Countdown auf 1 reduziert. Dadurch
explodiert diese Bombe im nächsten Frame ebenfalls.
Wenn sich in der Reichweite ein BLOCK oder coveredItem befindet, wird an dieser Stelle
ebenfalls eine Flamme erzeugt. Es wird allerdings erst im letzten Frame, in dem die Flamme
angezeigt wird, auch der BLOCK auf empty gesetzt, bzw. das covered auf false, damit ein
leeres Feld oder das entsprechende Upgrade. Der Grund dafür war recht simpel: wenn
bereits zu Beginn die Blöcke entfernt wurden, wurden im nächsten Schleifendurchlauf auch
noch dahinter liegende Blöcke entfernt. Da aber nur der erste erreichte Block in jede
Richtung weggesprengt werden sollte, musste dies unterbunden werden.


### Erste Tests
In den Usertests lief das Spiel jeweils in deutlich niedrigerer fps als die geplanten 60,
wodurch die Animation der Bomben deutlich länger andauerte, als eigentlich geplant.
Zudem kam von den Testern der Vorschlag, dass es nicht mehr möglich sein sollte, über
gelegte Bomben zu laufen. Der letzte Vorschlag wurde bis zum zweiten Usertest
eingebunden, und hat den Testern deutlich besser gefallen.


### Nachteile/Probleme
Wie bereits bei der Bewegungsanimation ist auch die Bombenanimation komplett von den
frames per second abhängig, statt von der Zeit. Um ein flüssigeres Spielen bei niedriger fps
zu ermöglichen, muss die Animation komplett umgeschrieben werden, und nur von der
vergangenen Zeit abhängig gemacht werden.



# Siegbedingungen
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
