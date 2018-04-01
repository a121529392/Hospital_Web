/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function checkdel() {
    document.getElementById('delsub').disabled = true;
     document.getElementById('Deleteloading').style.display = 'block';
     document.getElementById("Deletedata").innerHTML = "";
    if (document.getElementById("Summary2").checked) {
        Sum2 = 1;
        delcheck1 = 0;
    } else {
        Sum2 = 0;
        delcheck1 = 1;
    }
    if (document.getElementById("HeartRate2").checked) {
        Heart2 = 1;
        delcheck2 = 0;
    } else {
        Heart2= 0;
        delcheck2 = 1;
    }
    if (document.getElementById("Sleep2").checked) {
        Sleep2 = 1;
        delcheck3 = 0;
    } else {
        Sleep2 = 0;
        delcheck3 = 1;
    }
    if (document.getElementById("ActivityHeart2").checked) {
        AcHeart2 = 1;
        delcheck4 = 0;
    } else {
        AcHeart2 = 0;
        delcheck4 = 1;
    }
    if (document.getElementById("ActivityDetail2").checked) {
        AcDetail2 = 1;
        delcheck5 = 0;
    } else {
        AcDetail2 = 0;
        delcheck5 = 1;
    }
}
function open(){
    if(delcheck1==1&&delcheck2==1&&delcheck3==1&&delcheck4==1&&delcheck5==1){
        document.getElementById('delsub').disabled = false;
        document.getElementById("Deletedata").innerHTML = "Success";
    }
}
function checkload(){
    if(finish1==1&&finish2==1&&finish3==1&&finish4==1&&finish5==1){
       document.getElementById('Deleteloading').style.display = 'none';
    }
}
function deletedata(){
    if(Heart2==1){
        var user = $("#user").val();
        var startdate = $("#deldate").val();
        var enddate = $("#deldate2").val();
        var table = "Heartrate_Detail";
        var data = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data);
        $.ajax({
            type: "Get",
            url: "DeleteData.jsp",
            data: data,
            cache: false,
            success: function (response) {
                
                delcheck2=1;
                checkload();
                open();

                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });   
    }
        if(Sleep2==1){
        var user = $("#user").val();
        var startdate = $("#deldate").val();
        var enddate = $("#deldate2").val();
        var table = "Sleep_Detail";
        var data = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data);
        $.ajax({
            type: "Get",
            url: "DeleteData.jsp",
            data: data,
            cache: false,
            success: function (response) {
                
                delcheck3=1;
                checkload();
                open();

                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });   
    }
        if(Sum2==1){
        var user = $("#user").val();
        var startdate = $("#deldate").val();
        var enddate = $("#deldate2").val();
        var table = "Summary";
        var data = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data);
        $.ajax({
            type: "Get",
            url: "DeleteData.jsp",
            data: data,
            cache: false,
            success: function (response) {
                
                delcheck1=1;
                checkload();
                open();

                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });   
    }
        if(AcHeart2==1){
        var user = $("#user").val();
        var startdate = $("#deldate").val();
        var enddate = $("#deldate2").val();
        var table = "Activity_Heart";
        var data = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data);
        $.ajax({
            type: "Get",
            url: "DeleteData.jsp",
            data: data,
            cache: false,
            success: function (response) {
                
                delcheck4=1;
                checkload();
                open();

                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });   
    }
        if(AcDetail2==1){
        var user = $("#user").val();
        var startdate = $("#deldate").val();
        var enddate = $("#deldate2").val();
        var table = "Activity_Detail";
        var data = {
            user: user,
            startdate: startdate,
            enddate: enddate,
            table: table
        };
        console.log(data);
        $.ajax({
            type: "Get",
            url: "DeleteData.jsp",
            data: data,
            cache: false,
            success: function (response) {
                
                delcheck5=1;
                checkload();
                open();

                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
                alert("error");
            }
        });   
    }
}