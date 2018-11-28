# dr ida pu

## IS53012A/B Computer Security Coursework^1 2018–

```
Due 16:00, Friday 30 November 2018 week 9, to be submitted tolearn.gold.ac.uk,
and to be presented in class/workshop on Monday 3 December 2018 week 10.
```
## Part I (10%)

Develop a software prototype in Java^2 to demonstrate how the RSA algorithms work using the simplified al-
gorithms and examples studied in the lectures/workshops. In particular, your prototype should demonstrate
how two primespandqare generated, how the random numbereis generated, where 0<e<randehas no
factor in common withr, and how the private keydand public key(e,n)are generated. As part of testing,
a good coursework may also demonstrate a special case when your RSA program wouldnotwork securely.
Your program should prompt the user to input certain parameters that would lead to the problematic state.
There is no specific requirement to the user interface of your prototype but you should design at least
a simple user interface to allow the user to simulate a communication scenario, where Alice sends an
encrypted message to Bob, and Bob decrypts the ciphertext to read the message. Also, Charlie may intercept
the data flow and obtain unauthorised information.
For example, the following format may be adopted to demonstrate what happens with the plaintextmthat
from Alice to Bob, where “??” parts are for you to design.

```
Alice Charlie Bob
m
↓
?? → ?? → ??
↓
?? ← ?? ← ??
↓ ↓
?? ??
↓
?? → ?? → ??
↓ ↓
?? ??
```
You may decide where to start your design but it would often be easier to first divide the task into a number
of subtasks. For example,

1. Implement a cryptorandom key generator and the algorithm for modular exponentiation.
2. Implement the RSA encryption algorithm.
3. Implement the RSA decryption algorithm.

You may add if necessary assumptions for details to ease your implementation, but you must explain them
clearly to gain credits.

### [END OF COURSEWORK ASSIGNMENT]

(^1) You are encouraged to work as a team of up to three members each playing a role as Alice, Bob or Charlie.
(^2) or any programming language freely available


# dr ida pu

## Part II (10%)

Based on the software prototype that you have developed in the previous part, analyse and implement the
protocol below about authentication using a trusted server S.

Suppose a trusted server S that distributes public keys on behalf of others. Thus S holds Alice’s public
keyKAand Bob’s public keyKB. S’s public key,kS, is well known. Now Alice (A) and Bob (B) wish to
authenticate with each other and they propose to use the following protocol.

```
1) Dear S, This is A and I would like to get B’s public key. Yours sincerely, A.
2) Dear A, Here is B’s public key signed by me. Yours sincerely, S.
3) Dear B, This is A and I have sent you a nonce only you can read. Yours sincerely, A.
4) Dear S, This is B and I would like to get A’s public key. Yours sincerely, B.
5) Dear B, Here is A’s public key signed by me. Yours sincerely, S.
6) Dear A, Here is my nonce and yours, proving I decrypted it. Yours sincerely, B.
7) Dear B, Here is your nonce proving I decrypted it. Yours sincerely, A.
```
1. Implement this protocol in Java^3 to demonstrate how it works (again in decimal). There is no specific
    requirement to the user interface of your prototype but you may like to use the same simple user
    interface in the previous coursework assignment.
2. Identify and in your program demonstrate if there is an error or/and a subtle vulnerability of this
    protocol. [Hint: Consider if A uses this protocol to authenticate with a third-party Z.]

You may add if necessary assumptions for details to ease your implementation, but you must explain them
clearly to gain credits. Also, you may decide where to start your implementation but it might be easier for
you to first work out the keys and notations involved in each step. For example, letnAandnBbe the nonce
of A and of B respectively, and(x,y)kbe(x,y)with a signaturek. The following lines denote the protocol
with information flows to be transmitted.

```
1) A→ S: A, B
2) S→ A:(KB,B)kS
3) A→ B:(nA,A)KB
4) B→S: B, A
5) S→ B:(KA,A)kS
6) B→A:(nA,nB)KA
7) A→ B:(nB)KB
```
### [END OF COURSEWORK ASSIGNMENT]

(^3) or any programming language freely available


# dr ida pu

## Submission requirements

These requirements apply to both parts.

1. Naming conventions for any.pdfor.zipfile submissions
    When naming your files, please ensure that you include your full name, student ID number, course code and
    assignment number, e.g.FAMILY-NAME.first-name_ID_IS53012A_cwPart#.pdf
    (e.g.ZUCKERBERG.david_920000000_IS53012A_cwPart2.pdf).
2. Your coursework submission must include a report Document [40%] and the program Code [60%].
    The Document (preferable in.pdfformat) should include the following sections:
       (a) Algorithms (in flow-chart)
(b) Design (in block diagram or class-diagram in UML)
(c) Demonstration (in 5 best screen-shots)
(d) Discussion (including answers to any questions/problems in the Coursework assignment, your experience
in attempt of the coursework, and full bibliography)
    The program code should include the
       (a) Java source codes.java
(b) executable version.class.
    If you use a programming language other than Java, you would need to provide the whole running environment
    for the marker.
3. Execution of your programs:
    [Penalty] A ZERO mark may be awarded if
       - your program(s) cannot be run from the coursework directory by a simple command
          ‘java menu’ (this means that you should name your main class ‘menu’, or adopt themenu.javathat
          can be found in the Appendix on page 4);
       - your source code(s) does not compile and you give no information on your program execution environ-
          ment;
       - your program(s) does not do what you claim it should do;
       - your program(s) crashes within the firstthreeinteractive execution steps;
       - your program(s) works for the first time of execution only;
       - there is no comment in your source code.
4. You should monitor and report the time you have spent for each part of the coursework answers, and leave a
    note to the examiner if you need to raise any issue at the beginning of your coursework answers as follows:

```
Total Number of Hours Spent
Hours Spent for Algorithm Design
Hours Spent for Programming
Hours Spent for Writing Report
Hours Spent for Testing
Note for the examiner (if any):
```
5. Showallyour work. Any use of others’ work should be declared at the point of use and referred to in the
    Bibliographysection at the end of your coursework answers.
6. Group work (by up to 3 members) is allowed but ALL member names must be displayed on the coversheet of
    the ONE group coursework submission, and an equal grade will be awarded to all members unless specified
    otherwise.



### [END OF SUBMISSION REQUIREMENTS]


