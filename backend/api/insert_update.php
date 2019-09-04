<?php
	require '../utilities/DbConnector.php';
	
	$dbcon = new DbConnector();
	$con = $dbcon->con();
	
	$title = $_POST['title'];
	$body = $_POST['body'];
	$sport = $_POST['sport'];
	
	
	
	switch ($sport) {
		
		case "soccer":
			$resultSoccer  = $con->query("INSERT INTO soccer_updates (`update_title`, `update_description`)
				values ('".$title."','".$body."' )");
			
			checkResult($resultSoccer);
			
			break;
		
		case "basketball":
			$resultBasketball = $con->query("INSERT INTO basketball_updates (`update_title`, `update_description`)
				values ('".$title."','".$body."' )");
			checkResult($resultBasketball);
			break;
		case "hockey":
			$resultHockey = $con->query("INSERT INTO hockey_updates (`update_title`, `update_description`)
				values ('".$title."','".$body."' )");
			
			checkResult($resultHockey);
			
			break;
		case "admin":
			$resultEvents = $con->query("INSERT INTO event_updates (`event_title`, `event_description`)
				values ('".$title."','".$body."' )");
			checkResult($resultEvents);
			break;
			
			
	}
	
	function checkResult($result){
		$response = array();
		if ($result){
			$code = "successful";
			$message = "Successful update";
			array_push($response, array("code"=>$code,"message"=>$message));
			echo json_encode($response);
		}
		elseif (!$result){
			$code = "failed";
			$message = "Something went wrong with the update";
			array_push($response, array("code"=>$code, "message"=>$message));
			echo json_encode($response);
		}
	}
	
	$con->close();