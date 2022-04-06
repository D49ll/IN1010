# Del 1: Finne subsekvenser
## Subsekvens
- En string med lengde 3: "ABC"
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

## Oppgave 3
Her skal vi lese fra en fil og finne alle subsekvensene til en linje.
1. Les en linje fra fil
1. Finn alle unike kombinasjoen av 3 tegn i linjen, tegnene må henge sammen

# Del 2: Finne mønster med tråder
Vi skal gjenbruke alle klassene som ble laget i del 1. De skal utvides slik de kan brukes i parallell (Threads)
1. Lage program som leser/behandle filer i parallell
1. Lage program som kan flette de behanlede filene i paralell

Én fil skal behandles av én tråd. Utfordring: begrens antall tråder.

## Oppgave 6
Opprett klassen Monitor1 som er komponert 