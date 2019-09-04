<?php
	
	require('../utilities/DbConnector.php');
	
	$dbconn = new DbConnector();
	$con = $dbconn->con();
	
	
	$rating = $_POST['rate'];
	$username = $_POST['username'];
	
	
	$result = $con->query("INSERT INTO rating (`username`,`rate`) VALUES ('" . $username . "', '" . $rating . "')");
	$response = array();
	
	if ($result) {
		$code = "successful";
		$message = "successful";
		array_push($response, array("code" => $code, "message" => $message));
		echo json_encode($response);
	}
	else{
		$code = "failed";
		$message = "failed to insert data";
		array_push($response, array("code" => $code, "message" => $message));
		echo json_encode($response);
	}
	
	$con->close();