// Agent sample_agent in project my1st_app

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+received_message(Message) <-
    .print("Messaggio ricevuto da Unity: ", Message);
    +connection_established.

+connection_established <- !reachDestination.

+!reachDestination <- sendMessage("reachDest").


{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moise/asl/org-obedient.asl") }
