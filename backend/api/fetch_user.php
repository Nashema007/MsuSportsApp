<?php
	/*
	 * delete user query and use join operation to display user name and if student or coach
	 */
	
	require '../utilities/DbConnector.php';
	
	$dbcon = new DbConnector();
	$con = $dbcon->con();
	
	$userQuery = $con->query("SELECT regnumber as id FROM student union all select idcoach from coach") ;
	$values = array();
	while($row =$userQuery->fetch_array()){
		
		array_push($values, array("id"=>$row['id']));
		
	}
	             echo json_encode($values);
	
	
	