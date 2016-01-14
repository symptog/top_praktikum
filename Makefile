.PHONY: praktikum.pdf watch

praktikum.pdf: content/praktikum.md
	pandoc --template=template/praktikum.tex \
			--filter pandoc-crossref \
			--filter pandoc-citeproc \
			--listings \
			--chapter \
			content/praktikum.md \
			content/Einleitung.md \
			content/Papierprototyp.md \
			content/implementierung/Grundgeruest.md \
			content/implementierung/Menue.md \
			content/implementierung/Steuerung.md \
			content/implementierung/Upgrades.md \
			content/implementierung/Bomben.md \
			content/implementierung/Siegbedingungen.md \
			content/User_Tests.md \
			content/Zusammenfassung.md \
			-o "build/praktikum.pdf"
	xdg-open build/praktikum.pdf

watch:
	while true; do inotifywait -e close_write . && make praktikum.pdf; done
