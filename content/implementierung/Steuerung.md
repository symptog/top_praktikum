## Steuerung

Am Anfang des Spielentwurfs stellte sich die Frage, wie eine Steuerung der Spielfiguren wohl am Besten zu realisieren wäre, damit das Spiel recht einfach und trotzdem ohne ständiges Hinsehen so gut wie möglich spielbar ist. Zur Auswahl standen folgende Möglichkeiten: eine direkte Steuerung durch Berühren der Figuren, eine Steuerung durch Pfeiltasten, oder die Steuerung mittels eines simulierten Trackballs. Der Punkt, welcher eine Entscheidung in diesem wichtigen Teil des Spiels mit entschied, ist die Tatsache, dass man an einem Tabletop kein haptisches Feedback auf seine Eingaben erhalten kann. Deshalb musste die Steuerung so einfach wie möglich gehalten werden, auch ohne ständiges Hinschauen spielbar sein und ein Chaos unter den Mitspielern verhindern. Also wurden Argumente für und gegen die verschiedenen Varianten gesammelt.
Eine direkte Steuerung der Spielfiguren durch Berühren dieser fiel recht schnell aus, denn das würde zu dem genannten Chaos unter den Spielern führen. Man müsste nämlich die ganze Zeit in Touchreichweite zur Figur sein, was eine ständige Bewegung mit Selbiger um den Tisch bedeuten würde. Diese Möglichkeit war somit untauglich.
Die nächste Option der Pfeiltasten klang schon besser, doch auch hier gab es Bedenken. Denn Pfeiltasten kann man auf einem Touchscreen zwar darstellen, jedoch erhält man nicht wie mit einer Tastatur eine spürbare Reaktion auf die Eingabe. Man kann somit nicht fühlen, ob bzw. welche Taste man gerade betätigt und wäre so wohl zum öfteren Hinsehen gezwungen. Außerdem muss man bei virtuellen Pfeiltasten mehrere Finger benutzen, die Finger recht genau platzieren und jeder Spieler hat dabei wohl eine andere Geschicklichkeit.
Also fiel die Entscheidung zugunsten eines einzelnen beweglichen Elements, was man auch in anderen Spielen für Smartphones und Tablet findet. Eine Art Ball, den man in einem gewissen Rahmen über den Bildschirm zieht, einen virtuellen Trackball. Diese Art der Steuerung sollte nicht sonderlich schwierig sein, benötigt bloß einen Finger zur Eingabe und ermöglicht trotzdem eine Bewegung in alle Richtungen. Denn hat man den Trackball einmal mit dem Finger berührt, so folgt dieser in die Bewegungsrichtung des Fingers und ist somit quasi blind bedienbar.

### erste Versuche der Entwicklung mit Processing
Auf der Suche nach einem passenden Tool für die Entwicklung des Spiels wurde zuerst eine Programmiersprache in Betracht gezogen, die auf Grafiken und Animationen ausgerichtet ist. Sie nennt sich Processing und wurde als quelloffenes Projekt am Massachusetts Institute of Technology entwickelt. Da sie eine Beschreibung von Formen und Objekten darstellt, erschien sie anfänglich perfekt für das Spiel. Jedoch stellte sich später heraus, dass Java vielleicht doch die geeignetere Programmiersprache ist.
Da wir die Entwicklung des Projekts jedoch in Processing begonnen haben, fiel auch die erste Entwicklung einer Steuerung auf dieses Tool. Am Anfang galt es zu überlegen, wie eine einfache Steuerung denn wohl aussehen müsste, damit sie als solche auch wahrgenommen würde und trotzdem intuitiv bedienbar ist. Was wäre da einfacher als ein Kreis, der geviertelt ist? Jedes Viertel steht dabei für eine Bewegungsrichtung und überträgt diese Richtung auf den Bomberman. Und genau das wurde auch der erste Entwurf.
Um einen Kreis in Processing zeichnen zu können, muss man zunächst in einer Setup-Funktion eine Leinwandgröße festlegen und anschließend kann man in der Draw-Funktion Befehle einfügen, die in jedem Frame ausgeführt werden. Für einen Trackball muss man dabei eine Linienfarbe und eine Füllfarbe festlegen und kann nun im Anschluss die eigentliche Form zeichnen. Im konkreten Projekt bedeutete dies einen großen Kreis ohne Füllung zu zeichnen (nachfolgend: Außenkreis), zwei Diagonalen hindurch zu ziehen damit Viertel entstehen, einen kleinen Kreis in der Mitte zu platzieren um eine Ruheposition zu simulieren und über all das einen beweglichen, farbigen Kreis (nachfolgend: Trackball) zu zeichnen. Dieser sollte nun dem Mauszeiger, später dann dem eigentlichen Finger in seiner Bewegung folgen und dadurch die Bewegungsrichtung anzeigen. Die Umsetzung dessen war auch gar nicht so schwer, jedoch verlangte sie ein wenig Mathematik.
Zeichnet man nämlich einen Trackball wie gerade beschrieben, so folgt dieser der Maus über die gesamte Leinwand. Das ist jedoch nicht gewünscht, denn der Trackball soll sich nur innerhalb des Außenkreises, der als Grenze dienen soll, bewegen. Was man deshalb tun muss, ist die Position der Maus bzw. des Fingers zu prüfen und bei einem Überschreiten der Grenze des Radius des Außenkreises - dem Radius des Trackballs, dessen weitere Bewegung zu verhindern. Man führt quasi eine unsichtbare Grenzlinie ein, die sich aus dem Radius des Außenkreises subtrahiert mit dem Radius des Trackballs beschreiben lässt. Und sobald die Maus diesen unsichtbaren Kreis verlässt, bewegt sich der Trackball nur noch auf dieser unsichtbaren Grenzlinie.
Processing bietet für diese Aufgabe einen einfachen Weg an, denn man kann sich die Position der Maus mit den Funktionen mouseX und mouseY zurückgeben lassen, mittels PVector einen Vector aus diesen beiden Angaben erzeugen und diesen Vektor am Mittelpunkt des Außenkreises ansetzen. Nun lässt sich mittels dist-Befehl die Entfernung zwischen dem Außenkreismittelpunkt und der Mausposition berechnen und sollte diese größer als die unsichtbare Grenze sein, so wird der aufgestellte Vektor einfach normalisiert und mit der maximal möglichen Position des Trackballs multipliziert. Schon läuft der Trackball nur noch innerhalb des Außenkreises und verlässt diesen nicht mehr, selbst wenn die Maus bzw. der Finger es tut.
Zur Kontrolle, ob der Trackball auch die richtige Richtung der Bewegung darstellt, lässt sich noch eine einfach Ausgabe der Richtungen in Textform einbauen, die im jeweiligen Viertel des Außenkreises aktiviert wird. Und neben dem Trackball gehörte auch noch ein Knopf zum Bombenlegen (nachfolgend: Bombknopf) zum ersten Entwurf der Steuerung.

### Eingliederung des Processing-Prototyps in Java
Nachdem die Entwicklung des Prototyps der Steuerung beendet war, war inzwischen auch die Programmiersprache unseres Projekts eine Andere. Es wurde nun in Java weiterentwickelt, was eine Migration der Steuerung nötig machte. Glücklicherweise ist Processing auf Java aufgebaut und ermöglicht auch einen Export der geschriebenen Projekte in Java, wodurch der Schritt nicht zu schwierig war. Nach dem Exportieren wurde der erzeugte Code in eine neue Java-Klasse eingefügt und mit minimalen Veränderungen lief das Ganze dann auch.

### Erweiterung und Anpassung der Steuerung
Nach dem erfolgreichen Einbau der Steuerung in Java, mussten die bis jetzt einzeln entwickelten Teile zusammengefügt werden. Das hieß, dass nun echte Funktionen der Bomberman angesprochen werden mussten, wenn sich der Trackball bewegt und auch die Bomben müssen von den Spielfiguren gelegt werden können. Zum Erreichen dessen musste nur die bisherige Ausgabe der Richtung mit je einem Funktionsaufruf ersetzt werden und auch das Touchevent des Bombknopfs wurde mit der entsprechenden Funktion, eine Bombe zu legen, versehen. Schon konnte erstmals richtig mit der Steuerung getestet werden, was auch in diesem Fall sehr gut funktionierte.
Als folgender Arbeitsschritt galt es noch, die ganz am Anfang genannten Wünsche einer Anzeige zu integrieren, was direkt in Java geschah. Dazu wurde die Position des Steuerkreises inklusive Trackball ein wenig weiter nach rechts verschoben, der Bombknopf ein wenig links und somit entstand dazwischen ein Raum für die Anzeige. Doch welche Items sollen überhaupt gezeigt werden?
Wie anfänglich schon im Paper Prototype festgestellt, ist eine Anzeige der Bombenanzahl, sowie deren Reichweite sinnvoll. Weitere Möglichkeiten waren die Anzahl der Leben, oder die Geschwindigkeit, mit der der Bomberman über das Spielfeld läuft. Da man Letztgenanntes nicht unbedingt wissen muss, da man es während des Spiels selbst erfährt, wurde die Anzahl der Leben noch mit aufgenommen. Diese Information ist deshalb für den Spieler interessant, da man so weiß, wie vorsichtig man sein sollte und wie es um die anderen Spieler bestellt ist. Außerdem kann man durch das Einsammeln eines Items seine Anzahl an Leben wieder erhöhen und dadurch seine Siegchance wahren.
Zur Anzeige der verschiedenen Parameter wurden die entsprechenden Bilder der Items in Mittellage des Steuerpanels platziert und in deren Vordergrund ein Zahlenwert geschrieben. Der Spieler kann sich dadurch einen schnellen Überblick über den Zustand seines Bomberman verschaffen und begegnet bekannten Symbolen, weshalb ein Verständnis leicht fallen sollte.
Zur weiteren Unterstützung des Spielers wurde noch der Außenkreis um den Trackball in der jeweiligen Spielerfarbe eingefärbt. Somit weiß jeder Spieler, welcher Bomberman zu ihm gehört, falls dies einmal vergessen werden sollte.

### Bewegung der Spielfigur

Die Spielfiguren werdem vom Spieler über die `UserArea` gesteuert. Der Spieler hat die Möglichkeit seine Figur innerhalb des Blockrasters auf den begebaren Blöcken zu bewegen. Dabei kann die Bewegung in vier Richtungen erfolgen: oben, unten, links und rechts.

Die Eingabe über die `UserArea` ruft die Bewegungsmethoden in der Klasse `Bomberman` auf. In dieser Klasse existiert eine eindeutige Zuordnung zu einer Koordinate im Blockraster und somit eine eindeutige Zuordnung zum Spielfeld. Mit jeder Bewegung der Spielfigur wird diese Rasterzuordnung neu berechnet. Der Spieler bewegt seine Spielfigur Pixelweise horizontal und vertikal durch die Bewegungsmethoden. Die Figur bewegt sich dabei auf virtuellen Linien, die die Mittelpunkte der Blöcke miteinander verbinden. Mit jedem Frame wird überprüft, ob die Spielfigur sich zu einem weiteren Block bewegen kann. Dabei wird überprüft, ob sich der Rand der Spielfigur in einen Block bewegt, der betreten werden darf. Darf dieser Block nicht betreten werden, so kann sich die Spielfigur in Richtung dieses Blocks nicht weiter bewegen. Die Abfrage beschränkt sich zudem auf die der Spielfigur direkt angrenzenden Blöcke oder den Spielfeldrand.

Durch die Bewegung auf der virtuelen Linie, müsste sich die Spielfigur direkt im Mittelpunkt eines Blockes befinden, um von einer vertikalen in eine horizontale Bewegung, oder umgekehrt, überzugehen. Mit Hilfe eines Tolleranzberieches um den Mittelpunkt des Blockes herum, kann die Spielfigur zwischen einer vertikalen und horizontalen Linie übergehen. Ein zu kleiner Tolleranzbereich hemmt dabei aber den Spielfluss, da es für den Spieler zu schwierig ist seine Figur in diesen Bereich zu steuern.

## Bewegungsanimation

Die Animation sollte insgesamt eher minimalistisch werden, jedoch trotzdem ansprechend wirken. Damit überhaupt eine Animation erkennbar ist, braucht man mindestens zwei verschiedene Bilder, die abwechselnd angezeigt werden. Es wurden also für
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
Bewegungsanimation ist jedoch am ehesten an einer Bewegung der Beine zu erkennen.
Der erste Schritt war also, den Bomberman so zu verändern, dass eine Bewegung der Beine erkennbar war. Dazu wurde einfach der
„Rock“, den unser Platzhalter Bomberman an hat, etwas angepasst, und das Resultat war mit
etwas Toleranz durchaus als Beine erkennbar. Dieses Bild (Blickrichtung unten, beide Beine
am Boden) wurde dann das Bild für den Ruhezustand.
Da der Bomberman quasi „nach unten“ schaut, wurden zuerst die Animationsbilder für die
`movedown()`Funktion entworfen. Hierzu wurde wieder unser Ruhezustand Bild genommen,
und daraus zwei neue Bilder entwickelt: auf einem Bild ist der linke Fuß angehoben, und auf
dem anderen Bild der rechte Fuß. Wenn die Bilder abwechselnd angezeigt werden, war
bereits einen simpler Bewegungsablauf erkennbar.
Als nächstes wurden die Bilder für die `moveup()` Funktion erstellt, also die Bewegung
nach oben. Hierzu wurde das Ruhezustand Bild einfach horizontal gespiegelt, und die Augen
und der Mund des Bomberman entfernt. Anschließend sind wieder zwei Versionen
entstanden, mit jeweils einem Bein angehoben.Für die seitlichen Bewegungen (also `moveleft()` und `moveright()`) mussten einige
Anpassungen vorgenommen werden: Der Docht, der aus dem Kopf des Bomberman ragt,
musste entsprechend positioniert werden, ebenso wurde jeweils nur ein Auge verwendet,
welches in Laufrichtung blickt. Der ursprüngliche Platzhalter, der als Vorlage benutzt wurde,
hatte jedoch nur einen linken Arm, mit dem er eine Bombe hält. Wenn sich der Bomberman
nach rechts bewegt, konnte man also keine Arme sehen, da der linke Arm vom Kopf verdeckt
wurde. Bei den Bildern für `moveright()` wurde dann kurzerhand ein kleiner verstümmelter
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
Befehl pro Frame in die gewünschte Richtung bewegt. Die Veränderung der Position in
den move Funktionen wurde also statt von einem festen Wert einfach von dem
Geschwindigkeits Attribut abhängig gemacht.
Die Bomberman haben ihre eigene draw Funktion, in der bestimmt wird, was auf dem
Bildschirm ausgegeben wird.
Vom Touchpanel kommt immer eines von 5 verschiedenen Signalen: direction=0 für
stehend, direction=10 für Bewegung nach oben, direction=20 für Bewegung nach unten,
direction=30 für Bewegung nach links und direction=40 für Bewegung nach rechts.
In der draw-Funktion unserer Bomberman wurden also abhängig von der direction
unterschiedliche Bilder ausgegeben. Bevor die Bilder gezeichnet wurden, wurden sie
einmalig gerendert und in einer Hashmap gespeichert.
Wenn die direction 0 war, der Bomberman also einfach nur da stand, wurde einfach nur das
Bild für den stehenden Bomberman gezeichnet.Wenn allerdings eine Bewegung dargestellt wird, mussten
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


### Nachteile/Probleme
Da die Animation komplett abhängig von den Frames ist, (also der fps (frames per second))
statt von der vergangenen Zeit, wirkt die Bewegung bei niedriger fps (bedingt durch z.B. zu
langsamer Rechner für die gewählte Auflösung) deutlich langsamer, als bei höherer fps.
Dieses Problem könnte behoben werden, indem eine Variante entwickelt wird, die nur von
der Zeit abhängig ist.
