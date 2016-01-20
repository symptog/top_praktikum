## Entwicklung der Bomben & deren Animation

Da die Programmierung der Bomben voraussichtlich recht umfangreich werden würde,
wurde entschieden, eine extra Klasse zu erstellen, statt es mit in die Bomberman Klasse
einzubinden. Ein Objekt der Klasse sollte immer genau eine Bombe darstellen, und alle damit
verbundenen Funktionalitäten (runterticken des Countdowns, danach alles im
Explosionsradius zerstören, bei weiteren Bomben diese sofort auslösen etc.) erfüllen.
Die nächste Frage war, wie es jetzt möglich war, dynamisch Objekte dieser Klasse erstellen
zu können. Damit dieser Aspekt möglichst simpel umgesetzt werden konnte, 
wurde zu Spielbeginn eine festgelegte Anzahl Bombenobjekte erzeugt, und diese dann
immer wieder zu benutzt.Damit es auf dem Spielfeld nicht zu unübersichtlich wird, wurde festgelegt, dass ein Spieler
maximal 5 Bomben gleichzeitig ablegen kann. Um dies zu realisieren, wurde ein Feld in der `field`- Klasse angelegt, welches 20
Objekte der Bombenklasse verwalten kann. 
Jeder Bomberman hat 5 festgelegte Feldelemente, die seine Bomben abspeicherten (z.B.
Spieler rot die Feldelemente 0-4). Jede Bombe hat Attribute für ihre Position, ob sie aktuell
genutzt wird (also ob sie auf dem Spielfeld liegt), ihre Reichweite bei der Explosion, und die
aktuelle Zeit, bis wann sie explodiert.
Wenn vom Touchpanel der Befehl zum Legen einer Bombe kam, wurde zuerst überprüft, ob
der Spieler noch eine Bombe verfügbar hat. Danach wurde in dem Bombenfeld im
entsprechenden Intervall (bei Spieler rot z.B. 0-4) nach einer ungenutzten Bombe gesucht,
und diese bekam die aktuelle Position des Spielers zugewiesen, wurde als genutzt markiert,
und der Countdown wurde initialisiert.

### Bombenanimation
Die Animation der Bomben muss wieder minimalistisch werden, jedoch sollte es
möglich sein, auf den ersten Blick grob abschätzen zu können, wie lang die Bombe ungefähr
noch benötigt, bis sie explodiert. Die Bombe benötigt ungefähr 3 Sekunden (also 180 Frames
bei 60 fps), bis sie selbstständig explodiert. Die entsprechende Zahl ist auf
der Bombe erkennbar (die 3 in grün, die 2 in gelb, die 1 in rot), und zeigt an, wie lange es
noch dauert. Zudem ist eine kleine Animation eingebunden, um zu
erkennen, dass die Bombe aktiv ist.
Hierzu wurden 7 verschiedene Bilder entworfen:
Zwei Bilder mit einer kleinen Bombe, die mit einer grünen 3 in der Mitte versehen sind, und
sich nur durch ihre Farbe am Dochtende unterscheiden (bei einem Bild war die Flamme am
Dochtende innen rot und außen gelb, bei dem anderen Bild war es umgedreht). Diese beiden
Bilder wechseln sich in der ersten Sekunde in einem angebrachten Takt ab.
Für die zweite Sekunde wurden 3 Bilder entwickelt. Die ersten 2 Bilder waren wie die Bilder
für die erste Sekunde, statt der grünen 3 war diesmal allerdings eine gelbe 2 auf den
Bomben zu sehen. Für das dritte Bild wurde die Bombe dann deutlich vergrößert. Die Idee
dahinter war, dass sich in den ersten 1 1/2 Sekunden nur die Flamme des Dochtes und die Zahl
ändert, und in den zweiten 1 1/2 Sekunden des Countdowns sich zusätzlich noch die Größe
der Bombe verändert, um zu verdeutlichen, dass sie gleich explodiert.
Für die dritte Sekunde entstanden 2 Bilder: eine kleine Bombe mit einer roten 1, und eine
große Bombe mit ebenfalls einer roten 1. Diese beiden Bilder werden dann in einem
schnellen Intervall abwechselnd angezeigt.
Im Quellcode wurde die Animation dann folgendermaßen eingebunden: Die Bilder wurden
wieder einmalig gerendert und in einer Hashmap gespeichert. In jedem Frame wurde der
Countdown um 1 verringert, und alle 10 Frames wurde ein anderes Bild angezeigt, nach
oben beschriebenem Muster.Zudem enstand für die Bomben noch ein weiteres Bild. Eine Flamme, die die Zerstörung bzw.
deren Ausbreitung darstellen soll.


### Algorithmus zum Wegsprengen der Blöcke/Bomberman
Nach Ablauf des Countdowns explodiert die Bombe und alles in ihrem Radius
wird zerstört. Eine der ersten Designentscheidungen, die bezüglich der Bomben getroffen
wurde, war, dass nur der erste Block in den jeweiligen Richtungen weggesprengt werden
soll, und nicht mehrere Blöcke hintereinander. Die Explosion entfaltet sich bei „freier Bahn“
(kein BLOCK/ STATIC/coveredItem/Feldende in den 4 Richtungen in der Reichweite)
komplett bis zu dem Radius, ansonsten stoppt sie entsprechend früher. Die
Flammen der Explosion bleiben ungefähr 1 Sekunde bestehen, also 60 Frames.
Es wurde also einen Algorithmus entworfen, der mittels einer Schleife in jede der 4
Richtungen (+ natürlich den Koordinaten, an dem die Bombe vorher lag) folgendes abprüft:
Bis die Explosion auf ein Hindernis stößt (STATIC/BLOCK/coveredItem/Feldende) wird in
jedem Feldelement eine Flamme angezeigt, und es wird abgeprüft, ob sich ein Bomberman
oder eine Bombe auf eben diesem Feld befindet. Wenn sich ein Bomberman auf diesem Feld
befindet, wird dessen `startDie` Methode aufgerufen, und für eine Bombe wird auf diese
Koordinaten die `bombexplode` Methode ausgeführt. Auf die `startDie` Methode wird im
Kapitel Sterbeanimation genauer eingegangen. Da die Bomben nicht direkt mit dem Feld
verknüpft sind (es wird lediglich vermerkt, dass eine Bombe an der entsprechenden Stelle
liegt, aber nicht welche), wird in der `bombexplode` Methode das Bombenfeld nach den
passenden Koordinaten durchsucht, und deren Countdown auf 1 reduziert. Dadurch
explodiert diese Bombe im nächsten Frame ebenfalls.
Wenn sich in der Reichweite ein BLOCK oder coveredItem befindet, wird an dieser Stelle
ebenfalls eine Flamme erzeugt. Es wird allerdings erst im letzten Frame, in dem die Flamme
angezeigt wird, auch der BLOCK auf empty gesetzt, bzw. das covered auf false, damit ein
leeres Feld oder das entsprechende Upgrade erscheint. Wenn bereits zu Beginn die Blöcke entfernt werden, würden im nächsten Schleifendurchlauf auch
noch dahinter liegende Blöcke entfernt. Da aber nur der erste erreichte Block in jede
Richtung weggesprengt werden darf, musste dies unterbunden werden.


### Nachteile/Probleme
Wie bereits bei der Bewegungsanimation ist auch die Bombenanimation komplett von den
frames per second abhängig, statt von der Zeit. Um ein flüssigeres Spielen bei niedriger fps
zu ermöglichen, muss die Animation komplett umgeschrieben werden, und nur von der
vergangenen Zeit abhängig gemacht werden.
