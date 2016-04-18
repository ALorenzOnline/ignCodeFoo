<?php

$fetch_info ="http://ign-apis.herokuapp.com/articles?startIndex=30&count=5";
$jsonO=file_get_contents($fetch_info);
$jsondecoded = json_decode($jsonO,true);

$arrayBG=array();
$arrayHead=array();
$arreaSubHead=array();
$slugArray=array();
$dateArray=array();
$tempLink="";
$slug="";
$newLink="";

$linkArray=array();
//var_dump($jsondecoded);

//$head=  $jsondecoded['data'][1]['metadata']["headline"];
//echo $head;
for($i=0; $i<5; $i++){
	$tempLink="";
	$arrayBG[$i] = "url(" . $jsondecoded['data'][$i]["thumbnail"].")";
	$arrayHead[$i] = $jsondecoded['data'][$i]['metadata']["headline"];
	if(isset($jsondecoded['data'][$i]['metadata']["subHeadline"])){
		$arraySubHead[$i] = $jsondecoded['data'][$i]['metadata']["subHeadline"];
		$arraySubHead[$i]=str_replace('"','\"',$arraySubHead[$i]);
		$arraySubHead[$i]=str_replace("'","\'",$arraySubHead[$i]);
	}
	else{
		$arraySubHead[$i]="";
	}
	$arrayHead[$i]=str_replace('"','\"',$arrayHead[$i]);
	
	$arrayHead[$i]=str_replace("'","\'",$arrayHead[$i]);
		$dateArray[$i] = $jsondecoded['data'][$i]['metadata']["publishDate"];
		$slugArray[$i] = $jsondecoded['data'][$i]['metadata']["slug"];
	
	
	
	}
	//echo $dateArray[1];
	
	for($j=0; $j< count($dateArray);$j++){
		//echo strlen($dateArray[$j]);
		
		for($k=0;$k<strlen($dateArray[$j]);$k++){
			if($k<=9){
				$newLink=$newLink.$dateArray[$j][$k];
				$newLink =str_replace("-","/",$newLink);
			}
		}
		$newLink="http://www.ign.com/articles/".$newLink."/".$slugArray[$j];
		$linkArray[$j]=$newLink;
		//echo $newLink;
		//echo $linkArray[$j];
		$newLink="";
	}
	$shortenHead="";
	$shortenSub="";
	for($i=0;$i<count($arrayHead);$i++){
		
		if(strlen($arrayHead[$i])>40){
			for($k=0;$k<strlen($arrayHead[$i]);$k++){
				if($k<40){
					//echo $arrayHead[$i][$k];
					$shortenHead=$shortenHead.$arrayHead[$i][$k];
				}
			}
			$arrayHead[$i]=$shortenHead."...";
		}
		if(strlen($arraySubHead[$i])>1){
				//echo $arraySubHead[$i];
				for($j=0;$j<strlen($arraySubHead[$i]);$j++){
						if($j<40){
						//echo $arrayHead[$i][$k];
						$shortenSub=$shortenSub.$arraySubHead[$i][$j];
						}
				}
				$arraySubHead[$i]=$shortenSub;
				if(strlen($arraySubHead[$i])>39){
					$arraySubHead[$i]=$arraySubHead[$i]."...";
				}
		}
			
			
			$shortenHead="";
			$shortenSub="";
		
	}
	
	
//$newLink="http://www.ign.com/articles/".$newLink."/".$slug;
//test= $arrayHead[3];
//$test= $arraySubHead[3];
//echo $newLink;

?>