
function getBuildingData() {

		var xmlhttp = new XMLHttpRequest();
		var url = "http://localhost:8081/building?";

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var myArr = JSON.parse(this.responseText);
				CreateBuildingTableFromJSON(myArr);
			}
		};
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
}

function delBuildingData() {

		var link = "http://localhost:8081/building?deleteId=";
		var e = document.getElementById("buildingIdSelect");
		var deleteid = e.options[e.selectedIndex].text;
		var url = link + deleteid;
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("DELETE", url, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
			  window.location.reload()
			}
		}
}

function postBuildingData() {
		
		var url = "http://localhost:8081/building?";
		var name = document.getElementById("buildingname").value;
		var description = document.getElementById("buildingdescription").value;
		var data = "name=" + name + "&" + "description=" + description;

		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xmlhttp.send(data);
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
			  window.location.reload()
			}
		}
}

function putBuildingData() {
	
		var url = "http://localhost:8081/building?";
		var e = document.getElementById("buildingSelect");
		var nevmodosit = e.options[e.selectedIndex].text;
		var descmodosit = document.getElementById("buildingdescmodosit").value;
		var data = "nevmodosit=" + nevmodosit + "&" + "descmodosit=" + descmodosit;
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("PUT",url,true);
		xmlhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xmlhttp.send(data);
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState==4 && xmlhttp.status==200)
			{
			  window.location.reload()
			}
		}
}

function showBuildingDropdown() {

		var xmlhttp = new XMLHttpRequest();
		var url = "http://localhost:8081/building?";

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var myArr = JSON.parse(this.responseText);
				CreateBuildingDropdownFromJSON(myArr);
			}
		};
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
}

function CreateBuildingTableFromJSON(JSONarray) {

        // EXTRACT VALUE FOR HTML HEADER. 
        var col = [];
        for (var i = 0; i < JSONarray.length; i++) {
            for (var key in JSONarray[i]) {
                if (col.indexOf(key) === -1) {
                    col.push(key);
                }
            }
        }

        // CREATE DYNAMIC TABLE.
        var table = document.createElement("table");
		table.className = "table";

        // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

        var tr = table.insertRow(-1);                   // TABLE ROW.

        for (var i = 0; i < col.length; i++) {
            var th = document.createElement("th");      // TABLE HEADER.
            th.innerHTML = col[i];
            tr.appendChild(th);
        }

        // ADD JSON DATA TO THE TABLE AS ROWS.
        for (var i = 0; i < JSONarray.length; i++) {

            tr = table.insertRow(-1);

            for (var j = 0; j < col.length; j++) {
                var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = JSONarray[i][col[j]];
            }
        }

        // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
        var divContainer = document.getElementById("showBuildingData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
}

function CreateBuildingDropdownFromJSON(JSONarray) {
	
		var obj, myObj, name;
	
        for (var i = 0; i < JSONarray.length; i++) {
				var name = document.getElementById("buildingSelect");
				var id = document.getElementById("buildingIdSelect");
				var nameOption = document.createElement("option");
				var idOption = document.createElement("option");
                nameOption.text = JSONarray[i]["name"];
				name.append(nameOption);
				idOption.text = JSONarray[i]["id"];
				id.append(idOption);
        }
}