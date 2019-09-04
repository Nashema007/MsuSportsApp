<?php
	require("../utilities/DbConnector.php");
	
	$id = $_POST["Regnum"];
	$pass = $_POST["Password"];
	
	$sc =new DbConnector();
	
	$conn = $sc->con();
	
	
	$queryCoach = "SELECT * FROM coach WHERE idcoach='".$id."' and Password='".$pass."'";
	$queryStudent = "SELECT * FROM student WHERE regnumber='".$id."' and Password='".$pass."'";
	$queryAdmin = "SELECT * FROM adminmsu WHERE user_id='".$id."' and Password='".$pass."'";
///echo $query;
	$resultCoach = mysqli_query($conn, $queryCoach);
	$resultStudent = mysqli_query($conn, $queryStudent);
	$resultAdmin = mysqli_query($conn, $queryAdmin);
	$response = array();
	
	if(mysqli_num_rows($resultCoach) > 0){
		
		$code = "Login coach";
		$querySport = "SELECT sport FROM coach WHERE idcoach='".$id."'";
		$row = mysqli_fetch_array(mysqli_query($conn, $querySport));
		array_push($response, array("code"=>$code,"message"=>$row['sport']));
		echo json_encode($response);
	}
	else if(mysqli_num_rows($resultStudent) > 0){
		
		$code = "Login student";
		$queryStudentSport = $conn->query("SELECT sport FROM student where regnumber='".$id."'");
		$row = $queryStudentSport->fetch_array();
		array_push($response, array("code"=>$code,"message"=>$row['sport']));
		
		echo json_encode($response);
		
		
	}else if(mysqli_num_rows($resultAdmin) > 0){
		
		$code = "Login admin";
		$message ="successful";
		array_push($response, array("code"=>$code,"message"=>$message));
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