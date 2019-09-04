<?php
require("../utilities/DbConnector.php");

$dbconn = new DbConnector();
$con = $dbconn->con();

$id = $_POST['username'];
$pass = $_POST['Password'];
$login_time = $_POST['dateTime'];




	$queryCoach = $con->query("SELECT * FROM coach WHERE idcoach='".$id."'");
	$queryStudent = $con->query("SELECT * FROM student WHERE regnumber='".$id."'");

	if($queryCoach->num_rows> 0)   {
		$passCoach = $con->query("UPDATE coach SET password = '".$pass."' ");
		$sport = $con->query("Select sport from coach where idcoach='".$id."'");
		$queryLoginTimeCoach = $conn->query("INSERT INTO login_log (`user_id`, `login_time`)
				VALUES ('".$id."', '".$login_time."')");
		
		checkResult($passCoach, "Coach", $sport);
		
	}
	elseif ($queryStudent > 0){
		$passStudent = $con->query("UPDATE student SET password = '".$pass."' ");
		$queryLoginTimeStudent = $conn->query("INSERT INTO login_log (`user_id`, `login_time`)
				VALUES ('".$id."', '".$login_time."')");
		checkResult($passStudent, "Student", "");
	}
	else{
		$code = "failed";
		$message = "user already in system";
		array_push($response, array("code" => $code, "message" => $message));
		echo json_encode($response);
	}
	
	function checkResult($result, $message, $sport){
		
		$response = array();
		
		if ($result) {
			$code = "successful";
			array_push($response, array("code" => $code, "message" => $message,"sport"=>$sport));
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
	