## Menü

Das Menü kann über eine kleine Touchzone neben der User Area erreicht werden. Jedem Spieler ist ein eigenes Menü zugeordnet. Über das Menü kann per Touchevent ein Submenü geöffnet und geschlossen werden. Zum Submenü gehören drei Buttons. "Beenden", "Neues Spiel" und "Aufgeben". Mit einem Touch auf einen dieser Buttons wird eine Checkbox, zugehörig zum vorher gedrückten Button, geöffnet und kann auch wieder geschlossen werden. Diese Checkboxen liefern dem Spieler eine Beschreibung der Aktion und enthalten zusätzlich einen Button "Ja" und einen Button "Nein". Diese beiden Buttons sollen verhindern, dass der Spieler eine Aktion ohne weitere Bestätigung ausführt. Der "Ja"-Button löst die jeweilige Aktion aus, der "Nein"-Button schließt die Checkbox und beide Bestätigungbuttons wieder.

Deklariert wird das Menü innerhalb der Userarea in der Funktion  `touch()`. Bei der Initialisierung des Menüs wird vorher geprüft, auf welcher Seite sich der Button befindet, denn die Spielerfarben rot und violett erfordern eine Spiegelung. Die Übergabewerte für das Menü umfassen die x- und y-Koordinate für den Start, die Größe des Buttons, die Eigenschaft, ob sich das Menü auf der Seite von rot/violett (1) oder orange/blau befindet (2), und die Bomberman-Spielfigur.

In der Klasse `Menu` werden die drei Buttons (`button_close`, `button_new`,\linebreak `button_surr`) des Submenüs durch die Klasse `Submenu` deklariert. An die Submenü-Buttons werden die Koordinaten des Startpunktes, die Größe, der Text des Buttons, die Seite (rot/violett oder orange/blau) und die Spielfigur übergeben. Um das Menü öffnen und schließen zu können, wird die private boolean-Variable `pop` angelegt und mit false deklariert (das Menü ist anfangs geschlossen), welche beim Touch auf `!pop` gesetzt wird. Danach wird je nach Entscheidung der if-else-Schleife das Submenu angezeigt (`pop` = true) oder geschlossen (`pop` = false). Das Schließen im else-Zweig übernimmt die SMT Funktion `clearChildren()`.

\begin{figure}[h]
    \centering
    \caption{Screenshot des Menüs}
    \includegraphics[width=\textwidth]{figures/Bombermann_menu.png}
    \label{fig:screenshot_menu}
\end{figure}

Innerhalb der Klasse `Submenu` gibt es ebenfalls wieder eine private boolean-Variable `pop`, die für das Schließen und Öffnen der Checkbox verantwortlich ist und auf die gleiche Art und Weise funktioniert, wie in der Menüklasse. Eine if-else-Schleife stellt fest, auf welcher Seite die Submenübuttons erstellt werden müssen, wodurch der x-Achsen-Startpunkt geändert wird. Der Text des Buttons wird in der Menüklasse mit übergeben und hier durch `button_name` dargestellt. Da jeder Submenü-Button eine andere Checkbox öffnet, wird innerhalb des Touchevents nach Typ des Buttons (Neues Spiel, Beenden, Aufgeben) unterschieden.

Innerhalb der Klasse `Checkbox` werden die Buttons für ""Ja" und "Nein" deklariert und nebeneinander angeordnet. Dem `button_yes` wird noch zusätzlich eine ID übergeben, von welchem Submenü-Button er aufgerufen wurde, um auch einzelne Aktionen für die Buttons bereitzustellen.

In der Klasse `ButtonNo` wird innerhalb des Touchevents der Aufruf für das Schließen der Checkbox bereitgestellt. Dies geschieht mit den SMT Funktionen `this.getParent().getParent().clearChildren()`.
Innerhalb der Klasse `ButtonYes` werden per switch die Funktionen der Submenübuttons bereitgestellt. Für das Aufgeben wird per if geprüft, ob der Bomberman noch lebt und wenn ja wird die Funktion `bomberman.lostgame()`
aufgerufen.
