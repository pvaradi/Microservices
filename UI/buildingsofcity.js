
function getData() {

		var xmlhttp = new XMLHttpRequest();
		var url = "http://localhost:8082/buildingsofcity?";

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var myArr = JSON.parse(this.responseText);
				CreateTableFromJSON(myArr);
			}
		};
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
}

function delData() {

		var link = "http://localhost:8082/buildingsofcity?deleteId=";
		var e = document.getElementById("deleteIdSelect");
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

function postData() {
		
		var url = "http://localhost:8082/buildingsofcity?";
		//var name = document.getElementById("name").value;
		var e = document.getElementById("nameSelect");
		var name = e.options[e.selectedIndex].text;
		//var description = document.getElementById("description").value;
		var e = document.getElementById("descriptionSelect");
		var description = e.options[e.selectedIndex].text;
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

function putData() {
	
		var url = "http://localhost:8082/buildingsofcity?";
		//var nevmodosit = document.getElementById("nevmodosit").value;
		var e = document.getElementById("nevmodositSelect");
		var nevmodosit = e.options[e.selectedIndex].text;
		//var descmodosit = document.getElementById("descmodosit").value;
		var e = document.getElementById("descmodositSelect");
		var descmodosit = e.options[e.selectedIndex].text;
		var data = "idmodosit=" + nevmodosit + "&" + "descmodosit=" + descmodosit;
		
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

function showDropdown() {

		var xmlhttp = new XMLHttpRequest();
		var url = "http://localhost:8082/buildingsofcity?";

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var myArr = JSON.parse(this.responseText);
				CreateDropdownFromJSON(myArr);
			}
		};
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
}

	
function showDropdownCityList() {

		var xmlhttp = new XMLHttpRequest();
		var url = "http://localhost:8080/city?";

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var myArr = JSON.parse(this.responseText);
				CreateDropdownCityListFromJSON(myArr);
			}
		};
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
}

function showDropdownBuildingList() {

		var xmlhttp = new XMLHttpRequest();
		var url = "http://localhost:8081/building?";

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var myArr = JSON.parse(this.responseText);
				CreateDropdownBuildingListFromJSON(myArr);
			}
		};
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
}
 
function CreateTableFromJSON(JSONarray) {

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
        var divContainer = document.getElementById("showData");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
}

function CreateDropdownFromJSON(JSONarray) {
	
        for (var i = 0; i < JSONarray.length; i++) {
		
				var nevmodosit = document.getElementById("nevmodositSelect");
				var id = document.getElementById("deleteIdSelect");
				
				var nevmodositOption = document.createElement("option");
				var idOption = document.createElement("option");
				
				nevmodositOption.text = JSONarray[i]["id"];
				nevmodosit.append(nevmodositOption);
				
				idOption.text = JSONarray[i]["id"];
				id.append(idOption);
        }
}

function CreateDropdownCityListFromJSON(JSONarray) {
	
        for (var i = 0; i < JSONarray.length; i++) {
		
				var name = document.getElementById("nameSelect");
				
				var nameOption = document.createElement("option");
				
                nameOption.text = JSONarray[i]["name"];
				name.append(nameOption);
        }
}

function CreateDropdownBuildingListFromJSON(JSONarray) {
	
        for (var i = 0; i < JSONarray.length; i++) {
		
				var desc = document.getElementById("descriptionSelect");
				var descmodosit = document.getElementById("descmodositSelect");
				
				var descOption = document.createElement("option");
				var descmodositOption = document.createElement("option");
				
				descmodositOption.text = JSONarray[i]["name"];
				descmodosit.append(descmodositOption);
				
                descOption.text = JSONarray[i]["name"];
				desc.append(descOption);
        }
}
