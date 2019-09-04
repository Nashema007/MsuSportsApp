<?php
	require '../utilities/RetrieveDetails.php';
	
	
	
	$response = array();
	$retrieve = new RetrieveDetails();
	
	array_push($response, array(
		"soccer_students"=>$retrieve->studentsSoccer(),
		"choose_soccer" => $retrieve->chooseStudentsSoccer(),
		"view_soccer" => $retrieve->viewStudentsSoccer()
	));
	
	echo json_encode($response);