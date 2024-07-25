// Agent sample_agent in project my1st_app

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true
    <- .print("hello world.");       
       sendMessage("Ciao mondo!").


+responseReceived(ResponseBody) <-
    .print("Pippooooo: ");
    +pippo.

+pippo <- .print("Ho salvato correttamente il belief").



{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moise/asl/org-obedient.asl") }
