% Programozási nyelvek (Java) javító és utóvizsga, 2017.01.30.

# Általános tudnivalók

Ebben az ismertetésben az osztályok, valamint a minimálisan szükséges
metódusok leírásai fognak szerepelni.  A feladatmegoldás során fontos
betartani az elnevezésekre és típusokra vonatkozó megszorításokat, illetve
a szövegek formázási szabályait.

Nem publikus láthatóságú segédfüggvények létrehozhatóak, a feladatban nem
megkötött adattagok és elnevezéseik is a feladat megoldójára vannak bízva.
Törekedjünk arra, hogy az osztályok belső reprezentációját a *lehető
legjobban védjük*, tehát csak akkor engedjünk, és csak olyan hozzáférést,
amelyre a feladat felszólít, vagy amit azt osztályt használó kódrészlet
megkíván!

A beadott megoldásodnak működnie kell a mellékelt tesztprogramokkal, de ez
nem elégséges feltétele az elfogadásnak. A megírt forráskód legyen kellően
általános és újrafelhasználható!

Az egyes feladatrészeknél két pontszám látható (pl. 5 + 1), ebből az első
az alappont, mely a helyességért, a feladatleírásban szereplő követelmények
teljesítéséért adható. A második egy pluszpont, mely az oktató megítélése
alapján akkor jár, ha a hallgató az adott feladatrészt a félévben tanult
alapvető programozási irányelvek betartásával készítette el, így levonandó
például optimalizálatlan, kódismétlésekkel teli vagy konvenciókat erősen
sértő kód esetén.

Használható segédanyagok:
[Java dokumentáció](https://bead.inf.elte.hu/files/java/api/index.html),
legfeljebb egy üres lap és toll.  Ha bármilyen kérdés, észrevétel felmerül,
azt a felügyelőknek kell jelezni, *NEM* a diáktársaknak!

*Figyelem!* Az a metódus, mely fordítási hibát tartalmaz, automatikusan
*nulla* pontot ér!

# Tesztelés

Az egyes részfeladatokhoz tartoznak külön tesztesetek, amelyeket a
feladatok végén jelöltünk meg.  Ezek önállóan is fordítható és futtatható
`.java` állományok a mellékelt `.jar` segítségével.  Például Windows alatt
az első feladathoz tartozó tesztesetek így fordíthatóak és futtathatóak:

~~~~
> javac -cp .;tests-Bag.jar tests/Part1.java
> java -cp .;tests-Bag.jar tests/Part1
~~~~

Ugyanezeket a teszteseteket használja a komplett feladathoz tartozó
tesztelést végző `Test` osztály is. Ezt Windows alatt így lehet
futtatni:

~~~~
> java -cp .;tests-Bag.jar Test
~~~~

Linux alatt mindent ugyanúgy lehet fordítani és futtatni, csak a `-cp`
paraméterében a pontosvesszőt kell kettőspontra cserélni.

A programhoz tartozó tesztesetek:
[egységtesztelő](/files/java/Bag-tests.zip).

# A feladat összefoglaló leírása

A feladatban egy zsák adatszerkezetet kell kétféleképpen megvalósítani.

A zsák olyan "halmazszerű" típus, amelyben egy elem többször is
előfordulhat. Így egy elemről nem csak azt kell tudnunk, hogy benne van-e a
zsákban, vagy sem, hanem azt is, hogy ha benne van, akkor hány példányban
(ez az elem multiplicitása).

# A feladat részletes ismertetéses 

## 1. rész: absztrakt adattípus (1 + 1 pont)

A `bag.datatype.Bag` legyen egy absztrakt osztály, amely a konkrét
megvalósítások őse lesz. Az osztálynak legyen egy típusparamétere: a benne
tárolt elemek típusa. A `Bag`-nek ne legyen adattagja, viszont tartalmazza
a következő *absztrakt* metódusokat:

 - logikai visszatérési értékű, paraméter nélküli `isEmpty`, amely megadja,
 hogy a zsák üres-e

 - egész visszatérési értékű, paraméter nélküli `size`, amely megadja, hogy
 a zsák összesen hány elemet tartalmaz (azokat az elemeket, amelyek
 többször is benne vannak a zsákban annyiszor kell számolni, ahányszor
 benne vannak)

 - egész visszatérési értékű, paraméter nélküli `count`, amely megadja,
 hogy a zsák összesen hány *különböző* elemet tartalmaz (azokat az
 elemeket, amelyek többször is benne vannak a zsákban csak egyszer kell
 számolni)

 - logikai visszatérési értékű `contains`, amely megadja, hogy a
 paraméterében kapott elem benne van-e a zsákban

 - egész visszatérési értékű `getMultiplicity`, amely megadja, hogy a
 zsákban hány példány található a paraméterében megadott elemből (ha az
 adott elem nincs benne a zsákban, akkor a metódus `0`-át adjon vissza)

 - `void` visszatérési értékű `add`, amely beleteszi a paraméterében
 megadott elemet a zsákba

 - `void` visszatérési értékű `add`, amely az első paraméterében megadott
 elemből a második paraméterében megadott számú példányt tesz bele a
 zsákba; ha a metódus második paramétere negatív, akkor a metódus dobjon
 `IllegalArgumentException` kivételt

 - `void` visszatérési értékű `remove`, amely a paraméterében megadott
 elemből *egy példányt* kivesz a zsákból (ha a zsák nem tartalmazza a
 megadott elemet, akkor a metódus nem csinál semmit)

 - `void` visszatérési értékű `remove`, amely az első paraméterében
 megadott elemből a második paraméterében megadott számú példányt vesz ki a
 zsákból (ha a zsákban az adott elem a megadott számnál kevesebbszer
 szerepel, akkor a metódus kiveszi az adott elem összes példányát, ha pedig
 a zsák egyáltalán nem tartalmazza a megadott elemet, akkor a metódus nem
 csinál semmit); ha a metódus második paramétere negatív, akkor a metódus
 dobjon `IllegalArgumentException` kivételt 

 - `void` visszatérési értékű `removeAll`, amely kiveszi a zsákból a
 paraméterében megadott elem *összes* előfordulását (ha a zsák nem
 tartalmazza a megadott elemet, akkor a metódus nem csinál semmit)

Tesztelő: `tests.Part1`

## 2. rész: zsák megvalósítása `Map` segítségével (10 + 2 pont)

Készítsd el a `bag.MapBag` osztályt, amely legyen a `bag.datatype.Bag`
leszármazottja.
Az osztálynak legyen egy típusparamétere: a benne tárolt elemek típusa.

A `MapBag` a zsák elemeit egy `Map` adatszerkezetben tárolja, ahol a `Map`
kulcsa a zsákban szereplő elem, a hozzá tartozó érték pedig az elem
multiplicitása.

A `MapBag` tartalmazzon egy paraméter nélküli konstruktort, amely létrehoz
egy üres zsákot. Ezen felül az osztály valósítsa meg a `Bag` összes
metódusát.

*Megjegyzés*: feltételezhetjük, hogy a zsákot csak olyan típussal
példányosítják, amely implementálja a `Comparable` interfészt.

Tesztelő: `tests.Part2`

## 3. rész: zsák megvalósítása `List` segítségével (10 + 2 pont)

Készítsd el a `bag.ListBag` osztályt, amely legyen a `bag.datatype.Bag`
leszármazottja.
Az osztálynak legyen egy típusparamétere: a benne tárolt elemek típusa.

A `ListBag` a zsák elemeit egy `List` adatszerkezetben tárolja, ahol a
lista az elemeket annyiszor tartalmazza, ahányszor benne vannak a
zsákban. (Az elemek sorrendjének nincs jelentősége.)

A `ListBag` tartalmazzon egy paraméter nélküli konstruktort, amely létrehoz
egy üres zsákot. Ezen felül az osztály valósítsa meg a `Bag` összes
metódusát.

*Megjegyzés*: feltételezhetjük, hogy a zsákot csak olyan típussal
példányosítják, amely implementálja a `Comparable` interfészt.

Tesztelő: `tests.Part3`

## 4. rész: kiegészítések (6 + 2 pont)

### `getItems` (2 + 1 pont)

Legyen egy paraméter nélküli `getItems` metódus, amely egy halmazban
(`Set`) visszaadja a zsák elemeit. (A visszaadott halmazban a zsákban lévő
többszörös elemek csak egyszer szerepelnek.)

Végezd el a következő feladatokat:

 - vedd fel a metódust az ősosztályba (`Bag`) absztrakt metódusként

 - valósítsd meg a metódust mindkét leszármazott osztályban (`MapBag`,
 `ListBag`)

### `addAll`, `isSubBagOf` (4 + 1 pont)

Vegyél fel két *nem* absztrakt metódust a `Bag` ősosztályba:

 - `void` visszatérési értékű `addAll`, amely a paraméterében kapott zsák
 összes elemét hozzáadja az aktuális zsákhoz (ha a paraméterben kapott
 zsákban egy elem többször is szerepel, akkor annyiszor kell hozzáadni,
 amennyiszer szerepel)

 - logikai visszatérési értékű `isSubBagOf`, amely eldönti, hogy az
 aktuális zsák rész-zsákja-e a paraméterben kapottnak (egy zsák akkor része
 egy másiknak, ha a másik nagyobb vagy egyenlő prioritással tartalmazza a
 zsák összes elemét)

*Megjegyzés*: ezt a két metódust nem a leszármazottakban, hanem az
ősosztályban kell megvalósítani a zsák többi műveletének felhasználásával.

Tesztelő: `tests.Part4`

## 5. rész: setMultiplicity, toString (6 + 2 pont)

Vegyél fel két *nem* absztrakt metódust a `Bag` ősosztályba:

 - `void` visszatérési értékű `setMultiplicity`, amely futása után az első
 paraméterében megadott elem pontosan annyiszor fog szerepelni a zsákban,
 amennyi a második paraméter értéke (ha többször szerepel, akkor a
 fölösleges elemeket törölni kell, ha kevesebbszer, akkor a hiányzó
 elemeket hozzá kell adni, ha pedig pontosan annyi van, amennyi kell, akkor
 nem kell csinálni semmit); ha a megadott multiplicitás negatív szám, akkor
 a metódus dobjon `IllegalArgumentException` kivételt a következő
 szöveggel: `"Multiplicity must be positive or zero."`

 - szöveges visszatérési értékű, paraméter nélküli `toString`, amely a
 következő formában állítja elő a zsák szöveges reprezentációját:

    - az elemeket szögletes zárójelben soroljuk fel
    
    - az elemeket vesszővel és szóközzel választjuk el egymástól
    
    - a felsorolásban az egyforma elemeket összevonjuk és
    `elem (multiplicitás)` formában állítjuk össze; a felsorolásban az
    elemek sorrendje tetszőleges lehet
    
    - például, ha egy egész számokat tartalmazó zsák az 1-es számot
    kétszer, a 2-es számot egyszer, a 3-as számot pedig ötször tartalmazza,
    akkor a metódus visszatérési értéke `[1 (2), 2 (1), 3 (5)]` legyen

*Megjegyzés*: ezt a két metódust nem a leszármazottakban, hanem az
ősosztályban kell megvalósítani a zsák többi műveletének felhasználásával.

Tesztelő: `tests.Part5`

## 6. rész: rendezés (5 + 2 pont)

A `Bag` ősosztályba vegyél fel egy lista (`List`) visszatérési értékű,
paraméter nélküli, `getOrderedItems` nevű *nem* absztrakt metódust, amely
egy listában adja vissza a zsák összes elemét. Azok az elemek, amelyek a
zsákban többször szerepelnek, a listába csak *egyszer* kerüljenek benne. A
listában az elemek aszerint legyenek rendezve, hogy milyen multiplicitással
szerepelnek a zsákban (a legkisebbtől a legnagyobbig). Ha két elemnek
egyforma a multiplicitása, akkor nem számít, hogy melyik van előbb a
listában.

*Megjegyzés*: ezt a metódust nem a leszármazottakban, hanem az
ősosztályban kell megvalósítani a zsák többi műveletének felhasználásával.

Tesztelő: `tests.Part6`

## Pontozás

A tesztelő által adott értékelés csak becslésnek tekinthető, a
gyakorlatvezető ehhez képest levonhat vagy megadhat még pontokat.

  - 0  -- 17: elégtelen (1)
  - 18 -- 24: elégséges (2)
  - 25 -- 31: közepes (3)
  - 32 -- 38: jó (4)
  - 39 -- 49: jeles (5)
