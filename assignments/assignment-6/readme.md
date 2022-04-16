# Labyrint
- Innholder todimensjonalt Rute-array.
  - Referanse til alle rutene i lab.
  - Ta vare på antall rader og kolonner
  - Arrayet skal være privat
- Inneholde en toString metode som enkelt printer labyringen.

# Rute
- Ta vare på koordinater (x,y)
  - y er positiv nedover.
- Referanse til labyrinten den er en del av
- Nabopekere
  - Er en nabo dersom ruten som deles er av samme type.
- char tilTegn() som returnerer rutens tegnrepresentasjon
  - hvit = "."
  - svart = "#"
- Skal ikke kunne opprettes et objekt av Rute, kun subklasser

## HvitRute og SortRute
- Må implementere char tilTegn()

### Aapning
- Subklasse av HvitRute og bruker samme datastruktur som sin superklasse
- En åpning er definert der en den har en hvit nabo og ingen svart nabo.


