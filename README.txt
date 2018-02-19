DUMITRACHE Daniela Andreea
321CB
Tema1 - Paradigme de Programare

	Pentru evaluarea unui program am construit un AST pe baza expresiei de evaluat.
Elementele din arbore mostenesc o interfata comuna, numita Element, si se impart
in 2 categorii: noduri si elemente de baza (Variable, Value si Paranthesis). Am
realizat cate un nod pentru fiecare tip de operatie posibila: AssignNode (=), 
AssertNode (assert), AdditionNode (+), IfNode (if), ForNode (for), EqualityNode
(==), LessNode (<), MultiplyNode (*), ReturnNode (return) care mostenesc o clasa abstacta 
comuna, Node. De asemenea, am considerat si noduri de tip SemicolonNode (;), care
au cate 2 copii si ajuta la secventierea corespunzatoare a expresiilor.
Fiii unui nod de tip Semicolon corespund unor expresii aflate pe acelasi nivel.
	Fiecare element este caracterizat de un nume (care se va modifica pe
parcursul evaluarii, in functie de valoare nou calculata pentru nodul respecitv),
de o stare de visited (true sau false - folosita pentru parcurgere) si implementeaza 
functiile definite in interfata comuna, Element.
	Principiul decoratorului se aplica pentru clasa Node (si implicit clasele care
o mostenesc), deorarece, pe langa functionalitatile elementelor de baza, aceasta are
si o lista de copii si o functie de evaluare nevida, pentru a determina rezulatul operatiei
reprezentate de nod.
	Toate tipurile de element implementeazametoda accept, folosita de Visitor
in evaluarea arborelui.
	
	In constructia AST-ului am folosit 2 stive (una pentru operatori si una
pentru operanzi) pentru a parsa expresia si pentru a construi nodurile corespunzatoare
fiecarui element citit. Ultimul nod ramas in stiva de operanzi in urma parsarii reprezinta
radacina aroborelui.

	De asemenea, am construit o clasa Evaluator, care contine un HashMahp cu perechi
de tipul <Variabila, Valoare>, precum si metode pentru a verifica daca un nod contine 
drept copii variabile nedeclarate (pentru a verifica eroare de tip Check failed) si cate
o metoda pentru a verifica daca s-a ajuns la un rezultat de Assert failed, respectiv de 
Missing return.
	
	Pentru evaluarea programului am folosit deisgn pattern-ul Visitor. La finalul
apelului se va prelua si afisa in fisierul de iesire valoarea din radacina, care
contine rezultatul final al evaluarii expresiei date.Metodele de visit
realizeaza evaluarea fiecarui element in parte, in functie de tipul acestuia: 
	
	1) Metodele de visit pe elementele de baza au corpul vid.

	2) Metodele pentru evaluarea unui nod urmeaza urmatorii pasi:	
	 - verificarea rezultatelor metodelor de checkFailed si assertFailed, caz in 
care propagam mai departe rezultatul si nu mai realizam evaluarea copiilor, deoarece
nu ar mai avea rost. Verificarile au loc in toate tipurile de nod, cu exceptia celui de Assign,
unde nu ar avea sens, deoarece in acest tip de nod se realizeaza maparea intre variabile si valori.
	 - evaluarea copiilor si combinarea rezultatelor evaluarii in mod corespunzator pentru
fiecare tip de nod in parte: 
		* Maparea intre valori si variabile pentru AssignNode
		* Verificarea rezultatului din copil si setarea atentionarii de assertFailed
		  ca true pentru AssertNode
		* Adunarea rezultatelor obtinute pentru AdditionNode
		* Verificarea conditiei si preluarea rezultatului evaluarii din fiul al
		  doilea daca este adevarata, respectiv fiul al treilea daca conditia
		  este false, pentru IfNode
		* Setarea valorilor initiale si simularea unui for in felul urmator: se
		  evalueaza conditia, iar daca este adevarata, se executa programul din
		  interiorul for-ului (corespunzator celui de-al 4-lea copil). Se executa
		  pasul si se repeta procesul pana cand conditia devine falsa, pentru ForNode
		* Verificarea daca valorile celor doi fii sunt egale, pentru EqualityNode
		* Verificarea daca valoarea fiului din stanga este mai mica decat
		  a fiului din dreapta, pentru LessNode
		* Inmultirea valorilor celor 2 copii pentru MultiplyNode
		* Preluarea rezultatului din evaluarea copilului pentru ReturnNode
		* Preluarea rezultatului evaluarii fiului din dreapta pentru SemicolonNode
	 - setarea numelui nodului curent (care caracterizeaza valoarea din nod) in functie
de rezultatul evaluarii copiilor.

	Pentru nodurile care au copii de tip variabila, valorile corespunzatoare acestora
le preiau din HashMap-ul din clasa Evaloator. In caz ca valoarea unei variabile
se modifica pe parcursul evaluarii programului, valoarea acesteia va fi modificata corespunzator
si in HashMap.
	
	Am considerat oridinea prioritatilor erorilor ca fiind: Check failed > Missing Return >
Assert failed. De asemenea, in implementare, am realizat varianta greedy de verificare 
a corectitudinii,fiind verificate doar ramurile pe care programul ajunge in timpul 
executiei, nu toate cele existente.`

