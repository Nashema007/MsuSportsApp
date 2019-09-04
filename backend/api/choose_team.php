<?php
	require '../utilities/DbConnector.php';
	
	$dbcon = new DbConnector();
	$con = $dbcon->con();
	
	$regnum = $_POST['Player'];
	$sport= $_POST['sport'];
	
	
	switch ($sport){
		
		case "choose_soccer":
			
			$updateSoccer = $con->query("UPDATE soccer SET first_team = 'true' WHERE regnumber='".$regnum."'");
			
			checkResult($resultsSoccer);
			break;
		case "choose_basketball":
			
			$updateBasketball = $con->query("UPDATE basketball SET first_team = 'true' WHERE regnumber='".$regnum."'");
			checkResult($resultsBasketball);
			break;
		case "choose_hockey":
			
			$updateHockey = $con->query("UPDATE hockey SET first_team = 'true' WHERE regnumber='".$regnum."'");
			checkResult($resultsHockey);
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
