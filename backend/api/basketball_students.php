<?php
	require '../utilities/RetrieveDetails.php';
	
	
	
	$response = array();
	$retrieve = new RetrieveDetails();
	
	array_push($response, array(
		"basketball_students"=>$retrieve->studentsBasketBall(),
		"choose_basketball"=>$retrieve->chooseStudentsBasketBall(),
		"view_basketball"=>$retrieve->viewStudentsBasketBall()
	));
	
	echo json_encode($response);