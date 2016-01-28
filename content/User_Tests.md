
\begin{figure}[h]
    \centering
    \caption{Screenshot des Bomberman-Spiels}
    \includegraphics[width=\textwidth]{figures/screenshot01.png}
    \label{fig:screenshot01}
\end{figure}

# User Tests
## Erster Test
### User Feedback
#### Steuerung
Um die Praxistauglichkeit des Bomberman-Spiels zu testen, gab es einen User Test. Dadurch sollten wir eine Einschätzung von unbeteilichten Personen erhalten, was uns Aufschlüsse über die Spiellogik und die Funktionsfähigkeit geben sollte. Der erste Usertest wurde von einer Testgruppe, bestehend aus 4 Personen, durchgeführt. Anfangs wagten sich die Tester nur sehr vorsichtig an die Bedienung des Trackballs und des Bombuttons und nutzten für die Steuerung beider Elemente auch nur eine Hand. Nach wenigen Minuten jedoch, wurden dann beide Hände genutzt und das Spiel nahm an Fahrt auf.

#### Spiel
Das Sprengen von genügend Blöcken für einen Kontakt der Spieler dauerte sehr lang und aufgrund der geringeren Entfernung kam es nur zu Zweikämpfen direkt gegenüberliegender Spieler. Power-Ups, die dem Spieler ein gleichzeitiges Legen mehrere Bomben ermöglichen sollten, wurden nicht direkt erkannt und diese Möglichkeit wurde eher zufällig entdeckt. Das gezielte Abbiegen/um-die-Ecke-Laufen erwies sich als große Herausforderung, weshalb der Außenrand nur selten verlassen wurde. Dadurch wurden auch die Speed Power-Ups gemieden, welche es aufgrund der höheren Bewegungsgeschwindigkeit ebenfalls erschwerten abzubiegen. Die meisten Tode kamen durch Selbstverschulden zustande und weniger durch gegnerischen Einfluss. Dies führte auf dem fast vollständig freien Spielfeld am Ende dazu, dass die Spieler sich auf dem Außenrand bewegten und alle Bomben hintereinander legten. In dieser Phase war es fast unmöglich einen Gegner mit eigenen Aktionen gezielt auszuschalten.

#### Zusätzliches User Feedback
Ein Tester hatte Probleme die Übersicht über seine Spielfigur zu behalten, als er auf der gegenüberliegenden Seite unterwegs war. Die Lebensanzeige war nicht deutlich und es kam die Frage auf, ob die Anzeige die 0 entahlten sollte oder nicht. Die Anfangsphase war sehr langsam und träge, weil die Dauer bis man eine neue Bombe legen konnte sehr lang war.

#### Anschließende Änderungen

Um Verbesserungen an der Spieldauer vorzunehmen, wurde der Radius gelegter Bomben auf maximal 9 erhöht und die Zeit, nach der man eine neue Bombe erhält, wurde auf zwei Sekunden verringert. Um Spieler durch das Legen eigener Bomben bekämpfen zu können, wurde eine Änderung vorgenommen, die es verhindert, dass ein Spieler über Bomben laufen kann. Die Blöcke werden mit zufälligen Lücken verteilt, wodurch ca. 30% weniger Blöcke auf dem Spielfeld sind. Das soll die Zeit verkürzen, bis Spieler aufeinander treffen. Die Empfindlichkeit für das Abbiegen wurde ebenfalls verringert. Bei der Lebensanzeige entschied man sich für die allgemeine Umsetzung, dass eine Anzeige von „0 Leben“ zulässig ist und das letzte Leben darstellt.

## Zweiter Test

Im zweiten User Test wurde wieder mit einer Vierergruppe getestet. Diesmal wurden auch hier beide Hände zur Steuerung nicht von Anfang an eingesetzt, nach einer kurzen Eingewöhnungszeit arbeiteten dann aber doch alle Tester aktiv mit beiden Händen. Die Tester waren vollständig auf das Spiel konzentriert und es gab keine überraschenden Vorfälle, die das Spielgefühl negativ beeinflusst hätten.

###User Feedback
Die Empfindlichkeit der Steuerung beim Abbiegen an einer Ecke war zwar immernoch etwas unzuverlässig, aber nach einer kurzen Gewöhnungszeit durchaus annehmbar. Die Menüelemente des Submenüs waren nicht fest und konnten in einem gewissen Maß umher geschoben werden, aber einem gewissen Punkt verschwanden diese sogar.Die Umsetzung des Spielkonzeptes funktioniert und machte allen Spaß. 

#### Anschließende Änderungen
Um zu verhindern, dass die Submenüelemente verschiebbar sind, würde in den Klassen `Submenu`, `Checkbox`, `ButtonYes` und `ButtonNo` die Drag Funktion deaktiviert, indem `drag()` dort ohne Paremeter aufgerufen wird. Da der Test die Praxistauglichkeit des Bomberman-Spiels zeigte, wurden anschließend keine Veränderungen mehr am Spiel selbst vorgenommen.
