<?php
	require '../utilities/DbConnector.php';
	
	$dbcon = new DbConnector();
	$con = $dbcon->con();
	
	$regnum = $_POST['Player'];
	$sport= $_POST['sport'];
	
	
	switch ($sport){
		
		case "view_soccer":
			
			$updateSoccer = $con->query("UPDATE soccer SET first_team = '0' WHERE regnumber='".$regnum."'");
			
			checkResult($resultsSoccer);
			break;
		case "view_basketball":
			
			$updateBasketball = $con->query("UPDATE basketball SET first_team = '0' WHERE regnumber='".$regnum."'");
			checkResult($resultsBasketball);
			break;
		case "view_hockey":
			
			$updateHockey = $con->query("UPDATE hockey SET first_team = '0' WHERE regnumber='".$regnum."'");
			checkResult($resultsHockey);
			break;
		case "admin":
			
			$deleteCoachQuery= $con->query("SELECT * FROM coach WHERE idcoach='".$regnum."'");
			$deleteStudentQuery=$con->query("SELECT * FROM student WHERE regnumber='".$regnum."'");
			
			if($deleteCoachQuery->num_rows > 0) {
				$resultCoach = $con->query("DELETE FROM coach WHERE idcoach='" . $regnum . "'");
				 checkResult($resultCoach);
			}
			elseif ($deleteStudentQuery->num_rows > 0){
				$studentQuery = $con->query("DELETE FROM student WHERE regnumber='".$user."'");
				checkResult($studentQuery);
			}
			
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
