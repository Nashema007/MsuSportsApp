<?php
require '../utilities/RetrieveDetails.php';



$response = array();
$retrieve = new RetrieveDetails();

array_push($response, array(
	"sports"=>$retrieve->sports()
));

echo json_encode($response);