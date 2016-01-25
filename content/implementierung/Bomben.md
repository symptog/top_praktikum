## Entwicklung der Bomben & deren Animation

Da die Programmierung der Bomben voraussichtlich recht umfangreich werden würde,
wurde entschieden, dafür eine extra Klasse zu erstellen, statt sie mit in die `Bomberman`-Klasse
einzubinden. Ein Objekt der Klasse sollte immer genau eine Bombe darstellen und alle damit
verbundenen Funktionalitäten (Countdown, nachdem alles im Explosionsradius zerstört wird, weitere Bomben sofort auslösen etc.) erfüllen.
Die nächste Frage war, wie es jetzt möglich war, dynamisch Objekte dieser Klasse erstellen
zu können. Damit dieser Aspekt möglichst simpel umgesetzt werden konnte, 
wurde zu Spielbeginn eine festgelegte Anzahl an Bombenobjekten erzeugt und diese dann
immer wieder benutzt. Damit es auf dem Spielfeld nicht zu unübersichtlich wird, wurde festgelegt, dass ein Spieler
maximal fünf Bomben gleichzeitig ablegen kann. Um dies zu realisieren, wurde ein Feld in der `field`- Klasse angelegt, welches 20
Objekte der Bombenklasse verwalten kann. 
Jeder Bomberman hat genau fünf festgelegte Feldelemente, die seine Bomben abspeichern (z.B.
Spieler rot die Feldelemente 0-4). Jede Bombe hat Attribute für ihre Position, ob sie aktuell
genutzt wird (also ob sie auf dem Spielfeld liegt), ihre Reichweite bei der Explosion und die
aktuelle Zeit, bis wann sie explodiert.
Wenn vom Touchpanel der Befehl zum Legen einer Bombe kommt, wird zuerst überprüft, ob
der Spieler noch eine Bombe verfügbar hat. Danach wird in dem Bombenfeld im
entsprechenden Intervall (bei Spieler rot z.B. 0-4) nach einer ungenutzten Bombe gesucht
und diese bekommt die aktuelle Position des Spielers zugewiesen. Außerdem wird sie als genutzt markiert
und der Countdown initialisiert.

### Bombenanimation
Die Animation der Bomben sollte wieder minimalistisch werden, jedoch sollte es trotzdem
möglich sein, auf den ersten Blick grob abschätzen zu können, wie lang die Bombe ungefähr
noch benötigt, bis sie explodiert. Die Bombe benötigt ungefähr 3 Sekunden (180 Frames
bei 60 fps), bis sie selbstständig explodiert. Die entsprechende Zahl ist auf
der Bombe erkennbar (die 3 in grün, die 2 in gelb, die 1 in rot) und zeigt an, wie lange es
noch dauert. Zudem ist eine kleine Animation eingebunden, um zu
erkennen, dass die Bombe aktiv ist.
Hierzu wurden 7 verschiedene Bilder entworfen:
Zwei Bilder mit einer kleinen Bombe, die mit einer grünen 3 in der Mitte versehen sind und
sich nur durch ihre Farbe am Dochtende unterscheiden (bei einem Bild war die Flamme am
Dochtende innen rot und außen gelb, bei dem anderen Bild war es umgedreht). Diese beiden
Bilder wechseln sich in der ersten Sekunde ab.
Für die zweite Sekunde wurden drei Bilder entwickelt. Die ersten beiden Bilder sind wie die Bilder
für die erste Sekunde, statt der grünen 3 ist diesmal allerdings eine gelbe 2 auf den
Bomben zu sehen. Für das dritte Bild wurde die Bombe dann deutlich vergrößert. Die Idee
dahinter war, dass sich in den ersten 1,5 Sekunden nur die Flamme des Dochtes und die Zahl
ändert, in den zweiten 1,5 Sekunden des Countdowns sich zusätzlich noch die Größe
der Bombe verändert, um zu verdeutlichen, dass sie gleich explodiert.
Für die dritte Sekunde entstanden 2 weitere Bilder: eine kleine Bombe mit einer roten 1 und eine
große Bombe, ebenfalls mit einer roten 1. Diese beiden Bilder werden dann in einem
schnellen Intervall abwechselnd angezeigt.
Im Quellcode wurde die Animation dann folgendermaßen eingebunden: Die Bilder werden
wieder einmalig gerendert und in einer Hashmap gespeichert. In jedem Frame wird nun der
Countdown um 1 verringert und alle 10 Frames wird ein anderes Bild nach obigen Muster angezeigt.
Zudem enstand für die Bomben noch ein weiteres Bild. Eine Flamme, die die Zerstörung bzw.
deren Ausbreitung darstellen soll.


### Algorithmus zum Wegsprengen der Blöcke/Bomberman
Nach Ablauf des Countdowns explodiert die Bombe und alles in ihrem Radius
wird zerstört. Eine der ersten Designentscheidungen, die bezüglich der Bomben getroffen
werden musste, war, ob nur der erste Block in den jeweiligen Richtungen weggesprengt werden
soll und nicht mehrere Blöcke hintereinander. Die Explosion entfaltet sich bei „freier Bahn“
(kein BLOCK/STATIC/coveredItem/Feldende in den 4 Richtungen in der Reichweite)
komplett bis zu dem Radius, ansonsten stoppt sie entsprechend früher. Die
Flammen der Explosion bleiben ungefähr eine Sekunde bestehen, also 60 Frames.
Es wurde ein Algorithmus entworfen, der mittels einer Schleife in jede der 4
Richtungen (+ natürlich den Koordinaten, an dem die Bombe vorher lag) Folgendes abprüft:
Bis die Explosion auf ein Hindernis stößt (STATIC/BLOCK/coveredItem/Feldende), wird in
jedem Feldelement eine Flamme angezeigt und es wird geprüft, ob sich ein Bomberman
oder eine Bombe auf eben diesem Feld befindet. Wenn sich ein Bomberman auf einem dieser Felder
befindet, wird dessen `startDie`-Methode aufgerufen und für eine Bombe wird auf diese
Koordinaten die `bombexplode`-Methode ausgeführt. Auf die `startDie`-Methode wird im
Kapitel "Sterbeanimation" genauer eingegangen. Da die Bomben nicht direkt mit dem Feld
verknüpft sind (es wird lediglich vermerkt, dass eine Bombe an der entsprechenden Stelle
liegt, aber nicht welche), wird in der `bombexplode`-Methode das Bombenfeld nach den
passenden Koordinaten durchsucht und deren Countdown auf 1 reduziert. Dadurch
explodiert diese Bombe im nächsten Frame ebenfalls.
Wenn sich in der Reichweite ein BLOCK oder coveredItem befindet, wird an dieser Stelle
ebenfalls eine Flamme erzeugt. Es wird allerdings erst im letzten Frame, in dem die Flamme
angezeigt wird, auch der BLOCK auf empty gesetzt bzw. das covered auf false, damit ein
leeres Feld oder das entsprechende Upgrade erscheint. Wenn bereits zu Beginn die Blöcke entfernt werden, würden im nächsten Schleifendurchlauf auch
noch dahinter liegende Blöcke entfernt. Da aber nur der erste erreichte Block in jede
Richtung weggesprengt werden darf, musste dies unterbunden werden.


### Nachteile/Probleme
Wie bereits bei der Bewegungsanimation ist auch die Bombenanimation komplett von den
frames per second abhängig, statt von der Zeit. Um ein flüssigeres Spielen bei niedriger fps
zu ermöglichen, muss die Animation komplett umgeschrieben und nur von der
vergangenen Zeit abhängig gemacht werden.
