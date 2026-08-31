#import "@preview/finite:0.5.1" : automaton
#set page(width: auto, height: auto, margin: 0.5cm)

$ Sigma = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F, a, b, c, d, e, f, : ,
-, .] $

$ alpha = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F, a, b, c, d, e, f] $
$ beta = [:, -, .] $
#let n = 0
#align(center)[
#automaton(
    (
      q0:       (q1: $alpha$), //HEX
      q1:       (q2: $alpha$), //HEX
      q2:       (q3: $beta$), //S
      q3:       (q4: $alpha$), //HEX
      q4:       (q5: $alpha$), //HEX
      q5:       (q6: $beta$), //S
      q6:       (q7: $alpha$), //HEX
      q7:       (q8: $alpha$), //HEX
      q8:       (q9: $beta$), //S
      q9:       (q10: $alpha$), //HEX
      q10:       (q11: $alpha$), //HEX
      q11:       (q12: $beta$),//S
      q12:       (q13: $alpha$), //HEX
      q13:       (q14: $alpha$), //HEX
      q14:       (q15: $beta$),//S
      q15:       (q16: $alpha$), //HEX
      q16:       (q17: $alpha$), //HEX
    ),
    layout:(
      q0: (0, 7),
      q1: (2, 7),
      q2: (4, 7),
      q3: (6, 7),
      q4: (8, 7),
      q5: (10, 7),
      q6: (12, 7),
      q7: (12, 5),
      q8: (10, 5),
      q9: (8, 5),
      q10: (6, 5),
      q11: (4, 5),
      q12: (2, 5),
      q13: (0, 5),
      q14: (0, 3),
      q15: (2, 3),
      q16: (4, 3),
      q17: (6, 3),
    ),
      style: (
    // state: (radius: 0.45),      // Make state circles smaller (default is ~0.6)
    transition: (curve: 0),   // Flattens looping or curved arrows so they take up less space
  ),
    initial: "q0",
    final: "q17",
  )
]

