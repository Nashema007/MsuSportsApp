<?php
	require("../utilities/DbConnector.php");
	
	$id = $_POST["Regnum"];
	$pass = $_POST["Password"];
	$login_time = $_POST["Login"];
	
	$sc =new DbConnector();
	
	$conn = $sc->con();
	
	
	
	
	$resultCoach = $conn->query("SELECT * FROM coach WHERE idcoach='".$id."' and password='".$pass."'");
	$resultStudent =$conn->query("SELECT * FROM student WHERE regnumber='".$id."' and password='".$pass."'");
	$resultAdmin = $conn->query("SELECT * FROM adminmsu WHERE user_id='".$id."' and password='".$pass."'");
	$response = array();
	
	if($resultCoach->num_rows > 0){
		
		$code = "Login coach";
		$querySport = "SELECT sport FROM coach WHERE idcoach='".$id."'";
		
		$queryLoginTimeCoach = $conn->query("INSERT INTO login_log (`user_id`, `login_time`)
				VALUES ('".$id."', '".$login_time."')");
				
			$row = mysqli_fetch_array(mysqli_query($conn, $querySport));
			array_push($response, array("code"=>$code,"message"=>$row['sport'], "rating"=>""));
			echo json_encode($response);
	
		
	}
	else if($resultStudent->num_rows > 0){
		
		$code = "Login student";
		$queryStudentSport = $conn->query("SELECT sport FROM student where regnumber='".$id."'");
		
		$queryLoginTimeStudent = $conn->query("INSERT INTO login_log (`user_id`, `login_time`)
				VALUES ('".$id."', '".$login_time."')");
			
		$rating = "false";
		$queryRating = $conn->query("Select username from rating where username='".$id."'");
		if($queryRating->num_rows > 0){
			$rating = "true";
		}
			$row = $queryStudentSport->fetch_array();
			array_push($response, array("code" => $code, "message" => $row['sport'], "rating"=>$rating));
			echo json_encode($response);
		
	}else if($resultAdmin->num_rows > 0){
		
		$code = "Login admin";
		$message ="successful";
		array_push($response, array("code"=>$code,"message"=>$message, "rating"=>""));
		echo json_encode($response);
	}
	else{
		
		$code = "Login failed";
		$message = "Please Register to use this service";
		array_push($response, array("code"=>$code, "message"=>$message));
		echo json_encode($response);
	}
	
	mysqli_close($conn);

?>