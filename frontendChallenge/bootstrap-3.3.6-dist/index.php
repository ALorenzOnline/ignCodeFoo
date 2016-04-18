<!DOCTYPE html>
<?PHP

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

<script>
var test = "url('http://assets1.ignimgs.com/2016/04/13/battlebornartwork00jpg-6525aa_compact.jpg')";
var Link="<?php echo $tempLink?>";
function addListeners() {
	//document.getElementById("p0").innerHTML = "<?php echo $arrayHead[0]?>";
	document.getElementById("p0").innerHTML = "<a href='<?php echo $linkArray[0]?>'> <?php echo $arrayHead[0]?></a>";
	document.getElementById("p0S").innerHTML = "<?php echo $arraySubHead[0]?>";
	document.getElementById("p1").innerHTML = "<a href='<?php echo $linkArray[1]?>'><?php echo $arrayHead[1]?></a>";
	document.getElementById("p1S").innerHTML = "<?php echo $arraySubHead[1]?>";
	document.getElementById("p2").innerHTML = "<a href='<?php echo $linkArray[2]?>'><?php echo $arrayHead[2]?></a>";
	document.getElementById("p2S").innerHTML = "<?php echo $arraySubHead[2]?>";
	document.getElementById("p3").innerHTML = "<a href='<?php echo $linkArray[3]?>'><?php echo $arrayHead[3]?></a>";
	document.getElementById("p3S").innerHTML = "<?php echo $arraySubHead[3]?>";
	document.getElementById("p4").innerHTML = "<a href='<?php echo $linkArray[4]?>'><?php echo $arrayHead[4]?></a>";
	document.getElementById("p4S").innerHTML = "<?php echo $arraySubHead[4]?>";

	if(window.addEventListener){
		document.getElementById("0").addEventListener("mouseover",func,false);
		document.getElementById("0").addEventListener("mouseout",release,false);
		
		document.getElementById("1").addEventListener("mouseover",func1,false);
		document.getElementById("1").addEventListener("mouseout",release1,false);
		document.getElementById("2").addEventListener("mouseover",func2,false);
		document.getElementById("2").addEventListener("mouseout",release2,false);
		document.getElementById("3").addEventListener("mouseover",func3,false);
		document.getElementById("3").addEventListener("mouseout",release3,false);
		document.getElementById("4").addEventListener("mouseover",func4,false);
		document.getElementById("4").addEventListener("mouseout",release4,false);
	
	}
}
function func(){;
	document.getElementById("0").style.backgroundImage ="<?php echo $arrayBG[0]?>"
}
function func1(){;
	document.getElementById("1").style.backgroundImage ="<?php echo $arrayBG[1]?>"
}
function func2(){;
	document.getElementById("2").style.backgroundImage ="<?php echo $arrayBG[2]?>"
}
function func3(){;
	document.getElementById("3").style.backgroundImage ="<?php echo $arrayBG[3]?>"
}
function func4(){;
	document.getElementById("4").style.backgroundImage ="<?php echo $arrayBG[4]?>"
}
function release(){
	document.getElementById("0").style.backgroundImage = "";	
}
function release1(){
	document.getElementById("1").style.backgroundImage = "";	
}
function release2(){
	document.getElementById("2").style.backgroundImage = "";	
}
function release3(){
	document.getElementById("3").style.backgroundImage = "";	
}
function release4(){
	document.getElementById("4").style.backgroundImage = "";	
}
window.onload = addListeners;
</script>

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1"><!--for responsiveness of browser-->
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>
	<!--My CSS-->
	<link href="ign.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    
	<!--Use container-fluid to expand the whole width <div class="container">-->
	<!--columns can only be inserted into a row in cols first * is size
	(xs) for phones, (sm) for tablets,(md) for desktops,(lg) for large screens
	2nd * is # of columns-->
	<div  class="container-fluid">
		<div id="head" class=" row row1 ">
			<div id="test" class=" col-xs-6" >
			<p class="p2" align="right"><a href="index.php" style="color:red">ARTICLES</a></p>
			</div>
			<div class="col-xs-6">
			<p class="p3"> <a href="videos.php" style="color:white""> VIDEO</a></p>
			</div>
		</div>
		<div id="0" class=" row">
			<div class="col-xs-1">
			<h4>1</h4>
			</div>
			<div class="col-xs-11">
				<p id="p0" class"0"></p>
				<p id="p0S" class="subHead"></p>
			</div>
		</div>
		<div id="1" class="row">
			<div class="col-xs-1">
			<h4>2</h4>
			</div>
			<div class="col-xs-11">
				<p id="p1"></p>
				<p id="p1S" class="subHead"></p>
			</div>
			
		</div>
		<div id="2" class="row">
			<div class="col-xs-1">
			<h4>3</h4>
			</div>
			<div class="col-xs-11">
				<p id="p2"></p>
			<p id="p2S" class="subHead"></p>
			</div>
		</div>
		<div id="3" class="row">
			<div class="col-xs-1">
			<h4>4</h4>
			</div>
			<div class="col-xs-11">
				<p id="p3"></p>
				<p id="p3S" class="subHead"></p>
			</div>
			
			
		</div>
		<div id="4" class="row">
				<div class="col-xs-1">
			<h4>5</h4>
			</div>
			<div class="col-xs-11">
				<p id="p4"></p>
				<p id="p4S" class="subHead"></p>
			</div>
			
		</div>
		
		
		
		
		
		
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
