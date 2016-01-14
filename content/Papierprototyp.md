# Papierprototyp

Der erste Schritt in der Entwicklung des Bomberman-Spiels sollte, nach der Entscheidung für eine Spieleentwicklung und deren kurze Vorstellung bei unserem Betreuer, ein spielbarer Entwurf aus Papier, der Paper Prototype, sein. Dieser soll als erster Schritt in der Entwicklung von Softwareprojekten aufzeigen, was möglich ist und die Vorstellungen aller Teammitglieder erstmals zusammenbringen. Danach kann die Machbarkeit des Projekts überprüft werden und ein Fahrplan für die Entwicklung entworfen werden. Was sind die elementaren Bestandteile des Projekts, was muss zuerst entwickelt werden und was ist optional? So fingen auch wir mit dem Entwurf eines Papier-Prototypen an.

\begin{figure}[h]
    \centering
    \caption{Papierprototyp}
    \includegraphics[width=\textwidth]{figures/IMAG0149.jpg}
    \label{fig:papierprototyp}
\end{figure}

Die grundlegende Frage dabei war: Welche Elemente soll das Spiel einmal enthalten? Eine nicht ganz triviale Frage, denn einige Teammitglieder hatten schon konkretere Vorstellungen des Endproduktes als Andere. Und während einer anschließenden Recherche wurden sogar noch andere Erweiterungsmöglichkeiten und Spielformen gefunden.
Einige Elemente waren jedoch schon von Anfang an klar und gehören einfach zu einem Bomberman-Spiel hinzu. So beispielsweise die Jagd der Gegner durch das Legen von Bomben, dass Bomben eine gewisse Reichweite haben, man sich den Weg zu den Gegnern selbst erarbeiten muss und dass man die ersten beiden Parameter durch das Einsammeln entsprechender Items erhöhen kann. Außerdem sollte man eine gewisse Anzahl an Leben haben, um nicht sofort nach der ersten erfolgreichen Attacke eines Gegners aus dem Spiel zu sein.
Eine während der Recherche gefundene Erweiterung war die Idee, die Geschwindigkeit der Spielfigur durch ein Item erhöhen zu können und auch die Anzahl der Leben flexibler zu gestalten. Da diese beiden Dinge gut im Team ankamen, wurden sie mit in die Implementierung aufgenommen und letztendlich in das Spiel integriert.
Da sich das Team nun über einen groben Umfang an Ideen einig war, wurde in den folgenenden Teamtreffen mit dem Paper Prototype begonnen. Es wurde gezeichnet, geschnitten, gemalt und die Ideen aller zu einem Ganzen verarbeitet.
Während der Entwicklung des Prototyps fiel die Entscheidung erst mal auf ein einzelnes Spielfeldlayout, welches rechteckig sein sollte und dennoch genug Möglichkeiten für ein lustiges Spiel offen hält. Außerdem sollte jeder Spieler in seiner Ecke ein Steuerungsfeld erhalten, mit dem der Bomberman über den gesamten Tisch geführt werden kann und welches die wichtigsten Parameter seiner Spielfigur anzeigt. Das bedeutete zuerst nur die Anzahl der Bomben und deren Sprengkraft, was jedoch im fertigen Spiel noch erweitert wurde.
Damit die Spieler mit ihren Bomberman nicht den Durchblick verlieren, war eine Unterscheidung dieser nötig. Eine farbige Kennzeichnung der Bomberman sollte dieses Problem lösen und auch die Steuerelemente sollten diese Farbe tragen, um ein Durcheinander der Spieler auszuschließen. Aufgrund der farblichen Unterscheidung ist ein bequemes Spiel vom eigenen Platz aus möglich.
Ein weiteres nötiges Element war ein Menü, über welches der Spieler aus dem Spiel aussteigen, einen Neustart iniziieren, oder das gesamte Spiel komplett abbrechen kann.
Nach dem Ende der Bastelei am Paper Prototype sahen wir erstmals das vorläufige Produkt. Die Steuerung war hierbei das einfachste Element, die eigentliche Bewegung der Bomberman über das Spielfeld das Schwierigste. Denn in Papierform mussten alle Felder einzeln aufgedeckt, jede Bombe einzeln gelegt und jeder Block eigenständig bedient werden. Für den ersten Nutzertest des Prototypen bedeutete das eine Menge Arbeit und viele Dinge, auf die es zu achten galt.

## erste Spielversuche
Der erste Test des Spiels in der Papierversion fand mit nicht involvierten Spielern statt und verlief trotz des großen Aufwands recht erfolgreich. ... So konnte im Anschluss die Umsetzung des Bomberman-Spiels in die digitale Form beginnen.

* leicht verständliches Grundprinzip
* umständliche echtzeit Umsetzung
* positives feedback
* 2x Testergruppen

\todo[inline]{Auswertung der PP-Tests}
