document.addEventListener("DOMContentLoaded", function() {
  generateSelection();
});


function generateSelection(){
		var xhr1 = new XMLHttpRequest();
		xhr1.onload = function(){
			if(xhr1.status ===200){
				document.getElementById("category").innerHTML = xhr1.responseText;
			}
		}
		xhr1.open('POST','GetCategories.do',true);
		xhr1.send(null);
}


function generateTable(){
		category = String(document.getElementById("cat").value);
		var xhr = new XMLHttpRequest();
		xhr.onload = function(){
			if(xhr.status ===200){
				document.getElementById('inside').innerHTML = xhr.responseText;
			}
		}
		
		xhr.open('POST', 'ConnectDB.do',true);
		xhr.send(String(category));
	}