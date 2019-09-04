<?php
	require '../utilities/RetrieveDetails.php';
	
	
	
	$response = array();
	$retrieve = new RetrieveDetails();
	
	array_push($response, array(
		"hockey_students"=>$retrieve->studentHockey(),
		"choose_hockey"=>$retrieve->chooseStudentHockey(),
		"view_hockey"=>$retrieve->viewStudentHockey()
	));
	
	echo json_encode($response);