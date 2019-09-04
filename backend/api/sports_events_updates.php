<?php
	
	require "../utilities/SportEventsUpdates.php";
	
	$response = array();
	
	$request = new SportEventsUpdates();
	$dbcon = new DbConnector();
	$con = $dbcon->con();
	
	array_push($response, array(
		"events"=>$request->retrieveEvents(),
			"soccer"=>$request->retrieveSoccer(), 
			"basketball"=>$request->retrieveBasketBall(),
			"hockey"=>$request->retrieveHockey()
	));

		echo json_encode($response);
	      $con->close();
	
	?>
