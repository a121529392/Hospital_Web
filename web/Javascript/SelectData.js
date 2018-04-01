/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function check(){
    document.getElementById('sub2').disabled = true;
   
    if(document.getElementById("Summary").checked){
        document.getElementById('SumCSV').style.display = 'inline';
        document.getElementById('Sumdatatype').style.display = 'block';
        document.getElementById('Sumloading').style.display = 'block';
        document.getElementById("Sumdata").innerHTML = "";
        Sum=1;
        check1=0;
    }
    else{
        document.getElementById('SumCSV').style.display = 'none';
        document.getElementById('Sumdatatype').style.display = 'none';
        Sum=0;
        check1=1;
    }
    if(document.getElementById("HeartRate").checked){
        document.getElementById('HeartRateCSV').style.display = 'inline';
        document.getElementById('Heartdatatype').style.display = 'block';
        document.getElementById('Heartloading').style.display = 'block';
        document.getElementById("Heartdata").innerHTML = "";
        Heart=1;
        check2=0;
    }
    else{
        document.getElementById('HeartRateCSV').style.display = 'none';
        document.getElementById('Heartdatatype').style.display = 'none';
        Heart=0;
        check2=1;
    }
    if(document.getElementById("Sleep").checked){
        document.getElementById('SleepCSV').style.display = 'inline';
        document.getElementById('Sleepdatatype').style.display = 'block';
        document.getElementById('Sleeploading').style.display = 'block';
        document.getElementById("Sleepdata").innerHTML = "";
        Sleep=1;
        check3=0;
    }
    else{
        document.getElementById('SleepCSV').style.display = 'none';
        document.getElementById('Sleepdatatype').style.display = 'none';
        Sleep=0;
        check3=1;
    }
    if(document.getElementById("ActivityHeart").checked){
        document.getElementById('ACHeartRateCSV').style.display = 'inline';
        document.getElementById('ACHdatatype').style.display = 'block';
        document.getElementById('ACHloading').style.display = 'block';
        document.getElementById("ACHdata").innerHTML = "";
        AcHeart=1;
        check4=0;
    }
    else{
        document.getElementById('ACHeartRateCSV').style.display = 'none';
        document.getElementById('ACHdatatype').style.display = 'none';
        AcHeart=0;
        check4=1;
    }
    if(document.getElementById("ActivityDetail").checked){
        document.getElementById('ACDetailCSV').style.display = 'inline';
        document.getElementById('ACDdatatype').style.display = 'block';
        document.getElementById('ACDloading').style.display = 'block';
        document.getElementById("ACDdata").innerHTML = "";
    }
    else{
        document.getElementById('ACDetailCSV').style.display = 'none';
        document.getElementById('ACDdatatype').style.display = 'none';
    }
}
function checkid(){
   if(document.getElementById("ActivityDetail").checked){

        document.getElementById('actid').style.display = 'inline';
        document.getElementById('actidtext').style.display = 'inline';
        AcDetail=1;
        check5=0;
    }
    else{

        document.getElementById('actid').style.display = 'none';
        document.getElementById('actidtext').style.display = 'none';
        AcDetail=0;
        check5=1;
    }
}
function opensub2(){
    if(check1==1&&check2==1&&check3==1&&check4==1&&check5==1){
        document.getElementById('sub2').disabled = false;
    }
    
}


function SelectData() {
    if(Heart==1){
        var user = $("#user2").val();
        var startdate = $("#date").val();
        var enddate = $("#date2").val();
        var table = "Heartrate_Detail";
        var data1 = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data1);
        $.ajax({
            type: "Get",
            url: "SelectHeartData.jsp",
            data: data1,
            cache: false,
            success: function (response) {
                document.getElementById("Heartdata").innerHTML = response;
                document.getElementById('Heartloading').style.display = 'none';
                check2=1;
                opensub2();
                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });
    }
    if(Sum==1){
        var user = $("#user2").val();
        var startdate = $("#date").val();
        var enddate = $("#date2").val();
        var table = "Summary";
        var data1 = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data1);
        $.ajax({
            type: "Get",
            url: "SelectSumData.jsp",
            data: data1,
            cache: false,
            success: function (response) {
                document.getElementById("Sumdata").innerHTML = response;
                document.getElementById('Sumloading').style.display = 'none';
                check1=1;
                opensub2();
                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });
    }
    if(Sleep==1){
        var user = $("#user2").val();
        var startdate = $("#date").val();
        var enddate = $("#date2").val();
        var table = "Sleep_Detail";
        var data1 = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data1);
        $.ajax({
            type: "Get",
            url: "SelectSleepData.jsp",
            data: data1,
            cache: false,
            success: function (response) {
                document.getElementById("Sleepdata").innerHTML = response;
                document.getElementById('Sleeploading').style.display = 'none';
                check3=1;
                opensub2();
                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });
    }
    if(AcHeart==1){
        var user = $("#user2").val();
        var startdate = $("#date").val();
        var enddate = $("#date2").val();
        var table ="Activity_Heart";
        var data1 = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data1);
        $.ajax({
            type: "Get",
            url: "SelectACHeartData.jsp",
            data: data1,
            cache: false,
            success: function (response) {
                document.getElementById("ACHdata").innerHTML = response;
                document.getElementById('ACHloading').style.display = 'none';
                check4=1;
                opensub2();
                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });
    }
    if(AcDetail==1){
        var user = $("#user2").val();
        var id = $("#actid").val();
        var startdate = $("#date").val();
        var enddate = $("#date2").val();
        var table = "Activity_Detail";
        var data1 = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table,
            id: id
        };
        console.log(data1);
        $.ajax({
            type: "Get",
            url: "SelectACDetail.jsp",
            data: data1,
            cache: false,
            success: function (response) {
                document.getElementById("ACDdata").innerHTML = response;
                document.getElementById('ACDloading').style.display = 'none';
                check5=1;
                opensub2();
                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });
    }
}