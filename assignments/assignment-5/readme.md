## Subsekvens
- En string med lengde 3-4: "ABC"
- Finner en persons subsekvenser fra én fil
- Ønsker å finne om samme subsekvens går igjen hos andre personer (andre filer)
- Irrelevant om samme subsekvens forekommer flere ganger hos samme person

## Lagre subsekvenser
- En persons subsekvenser tilsvarer én hashmap
    - Dvs 1 innlest fil = 1 hashmap
    - Dvs N innlest filer = N hashmap
- For å finne antall forekomster flettes hashmapsene

## Oppgave 1

Klassen Subsekvens:
- public final (String) -> subsekvens 
- private antall (int) -