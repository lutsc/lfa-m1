#set page(width: auto, height: auto, margin: 0.5cm)


= Gramática regular
== Conjunto principal
$ Sigma = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F, a, b, c, d, e, f, : ,
-, .] $

== Subconjuntos utilizados
$ alpha = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F, a, b, c, d, e, f] $
$ beta = [:, -, .] $

== Transições
$ S-> alpha A quad quad  A-> alpha B quad quad  B-> beta C $
$ C-> alpha D quad quad  D-> alpha E quad quad  E-> beta F $
$ F-> alpha G quad quad  G-> alpha H quad quad  H-> beta I $
$ I-> alpha J quad quad  J-> alpha K quad quad  K-> beta L $
$ L-> alpha M quad quad  M-> alpha N quad quad  N-> beta O $
$ O-> alpha P quad quad  P-> alpha $
