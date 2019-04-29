# Obligatorisk øvelse 2 Håndbrekket

## Deloppgave 1 - Organiser teamet
### Team Handbrekket     
**Teamlead:** Jonas     
**Kundekontakt:** Emilia     

#### Gruppaskvalifikasjonar:
 * Marius: Java, JUnit, algoritmer, database
 * Jonas : Erfaring med github , Java, JUnit, algoritmer, database
 * Emilia : erfaring med prosjekt, Java, JUnit, algoritmer, database
 * Mari : Java, JUnit, algoritmer, database
 * Alba : Java , JUinit, algoritmer, github, 
 * Eirik : Java , JUinit, algoritmer, github, database
 * Nichola - Python


#### Verktøy vi brukar: 
 * Github project board
 * Maven 
 * IntelliJ

## Deloppgave 2 - Få oversikt over forventet produkt
### Overordnede målet for applikasjonen:
Det overordnende målet for applikasjonen er å implementere spillet Roborally. Lage et system som følger settet med Regler for dette spillet. 

### En liste over krav til systemet baser på høynivåkravene gjennomgått i forelesning:
Dette er listen over krav gjennomgått i forelesningen.
* Ha et spillbrett
* vise en brikke
* kunne flytte en brikke
* dele ut kort
* velge kort (5 av 9)
* flytte brikke utfra kort
* dele ut nye kort ved ny runde
* vise flere (i alle fall to) brikker på samme brett
* dele ut kort til hver robot
* flytte flere brikker samtidig
* flytte brikker utfra prioritet på kort
* flagg på brettet
* kunne registrere at en robot har vært innom et flagg
* håndtere konflikter i bevegelse riktig
* kunne legge igjen backup
* restarte fra backup v ødeleggelse
* går du i et hull, blir du ødelagt, mister et liv og begynner fra forrige backup
* går du av brettet, blir du ødelagt, mister et liv og begynner fra forrige backup
* blir du skutt i fillebiter (9 i skade) blir du ødelagt, mister et liv og begynner fra forrige backup
* vender en robot mot deg ved slutten av en fase blir du skutt og får en i skade
* får du skade får du mindre kort i henhold til skaden du har
* kan ikke gå gjennom vegger
* kunne ta powerdown for å reparere skade
* vanskelighetsgrad på brett??
* for mye skade brenner fast programkort fra runde til runde

### Prioritert liste over hvilke krav dere vil ha med i første iterasjon:
Per siste gruppemøte (06.02.2019) har vi følge liste over krav vi vil ha med i første iterasjon:
 * Interfaces for de klassene vi tenker å implementere til første iterasjon
 * Board klasse med enkle metoder
 * Square klasse med enkle metoder
 * Robot klasse med enkle metoder
 * Lage et enkelt spillbrett, vi tenker å vise det i terminalen først.
 * Main klassen skriver ut et spillbrett ved hjelp av Board klassen
 * Fylle Board klassen med Squares
 * Fylle eventuelle Squares med Robot

## Deloppgave 3 - Velg og tilpass en prosess for laget
### Beskrivelse av organisering og planlegging på prosjekt INF112
Planlegging og organisering av vårt prosjekt den første tiden skal være på følgende måte:

**Kommunikasjon:** Vi kommer til å ha aktiv kommunikasjon via Facebook Messenger og Slack mellom 
alle medlemmer i gruppen.

**Prosjekt (obligatoriske innleveringer):** På kort sikt planlegger vi å få på plass en grunnleggende struktur (Game og Card), 
og snarest mulig legge en mer langsiktig fremgangsplan. Vi følger med forretningsregler som står beskrevet i GitHub (master 
fra foreleser).

Dette er noe som kommer til å utvikle seg etter hvert vi ser hvordan det går med utviklingen 
av selve prosjektet.

**Dokumentasjon:** Vi dokumenterer alt som blir avtalt i våre møter på samme måte lager leder 
av gruppen en agenda for hva skal vi gjøre til neste møte. Dette er noe som vi fortsette å ha 
videre i organisering.

Vi kommer til å bruke Kanban for å ha en effektiv måte å følge med på prosjektet vårt.

**Fordeling av oppgaver:** Vi kommer til å jobbe med tester og par programmering. Dette kommer til å være spesiell 
brukt når vi begynner å jobbe mer med de avanserte oppgavene (Interface, kjerne «featurers», visualisering, osv.) i 
prosjektet vårt. Vi planlegger å fordele hoveddeler av prosjektet (gfx, spill-logikk osv.) til spesifikke personer 
(gjerne to eller flere) i gruppen som kommer til å ha ansvar for denne delen. Dette har vi tenkt til å gjøre for å sørge 
for at vi har en bedre forståelse for koden og for å gjøre organisering og planlegging fremover lettere. 
 
## Oppsummering
### Problemer
Ut ifra diskusjonen vår har vi kommet frem til at de to største problemene som vi støtte på i den første tiden i prosjektet.
Disse kan hovedsakelig oppsummeres som to hoved-utfordringer.

**Gruppe-sammenslåing:** Originalt ble vi delt inn i to grupper, en med fire medlemmer og en med fem. På grunn av manglende
oppmøte ble gruppene slått sammen for å sørge for at det ikke ble for mye arbeid. På grunn av dette har vi måtte bruke ekstra 
tid til å orientere gruppe-medlemmene, planlegge kode, oppdatere dokumentasjon osv. Dette resulterte i at fremgangen i prosjektet
den første uken gikk saktere enn planlagt.

**Kommunikasjon:** Vi hadde også noen problemer med kommunikasjon mellom medlemmene, spesielt etter sammenslåingen, da en av 
medlemmene ikke var blitt lagt til i Slack kanalen gruppen planlegger å bruke. I tillegg til dette var vi også dårlige på å 
sjekke Slack. Vi planlegger alle å bli flinkere på å sjekke det, men vi har også opprettet en Facebook gruppe-chat for å 
gjøre kommunikasjon lettere.

Ut ifra erfaringene fra den første uken og utfordringene vi har måtte løse har vi tenkt til å bli bedre på å følge opp hverandre
og kommunisere oftere, slik at det blir lettere å følge med på fremgangen i prosjektet.
