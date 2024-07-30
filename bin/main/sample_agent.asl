!start.

/* Plans */

+received_message(Message) <-
    .print("Messaggio ricevuto da Unity: ", Message);
    +connection_established.

+connection_established <- !reachDestination.

+!reachDestination <- 
    sendMessageToUnity("reachDest").

+event_happened(Message) <-
    .print("Andiamo verso il bianco");
    !box_pressed.

+!box_pressed <-
    .print("Raggiungi postazione bianca");
    sendMessageToUnity("reachWhiteDest").


{ include("$jacamo/templates/common-cartago.asl") }
{ include("$jacamo/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moise/asl/org-obedient.asl") }
