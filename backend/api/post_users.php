<?php
	require('../utilities/DbConnector.php');
	
	$dbconn = new DbConnector();
	$con = $dbconn->con();
	
	
	$accessLevel = $_POST['accessLevel'];
	$username = $_POST['username'];
	$sport = $_POST['sport'];
	$password = "default";
	
	switch ($accessLevel){
		
		case "Student":
			$resultStudent = $con->query("INSERT INTO student (`regnumber`,`password`) VALUES ('" . $username . "', '" . $password. "')");
			checkResult($resultStudent);
			break;
		case "Coach":
			$resultCoach = $con->query("INSERT INTO coach (`idcoach`,`password`, `sport`) VALUES ('" . $username . "', '" . $password. "', '".$sport."')");
			checkResult($resultCoach);
			break;
			
	}
	
	function checkResult($result){
		
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
		
	}
	
	
	$con->close();