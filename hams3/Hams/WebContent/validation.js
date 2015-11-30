function validate()
{
   var w=document.forms["myForm"]["patient_name"].value;
   var x=document.forms["myForm"]["clinic_detail"].value;
   //var y=document.forms["myForm"]["token_no"].value;
   var z=document.forms["myForm"]["time"].value;
   if (w.trim().length<=0) // check length of your name field
   {
      alert("Patient Name must be filled out");
      return false;
   }
   if (x.trim().length<=0) // check length of your name field
   {
      alert("clinic detail must be filled out");
      return false;
   }
   if (z.trim().length<=0) // check length of your name field
   {
      alert("Appointment time Name must be filled out");
      return false;
   }
   
   //contact number validation
   var mobile_no = document.forms["myForm"]["patient_mobile_number"].value;
   if (/^\d{10}$/.test(mobile_no)) {
       // value is ok, use it
   } else {
       alert("Invalid number; must be ten digits")
       number.focus()
       return false
   }
   
   //time validation
   var strval = document.forms["myForm"]["time"].value;

   var strval1;
     
   //minimum lenght is 6. example 1:2 AM
   if(strval.length < 6)
   {
    alert("Invalid time. Time format should be HH:MM AM/PM.");
    return false;
   }
   
   //Maximum length is 8. example 10:45 AM
   if(strval.lenght > 8)
   {
    alert("invalid time. Time format should be HH:MM AM/PM.");
    return false;
   }
   
   //Removing all space
   strval = trimAllSpace(strval); 
   
   //Checking AM/PM
   if(strval.charAt(strval.length - 1) != "M" && strval.charAt(
       strval.length - 1) != "m")
   {
    alert("Invalid time. Time shoule be end with AM or PM.");
    return false;
    
   }
   else if(strval.charAt(strval.length - 2) != 'A' && strval.charAt(
       strval.length - 2) != 'a' && strval.charAt(
       strval.length - 2) != 'p' && strval.charAt(strval.length - 2) != 'P')
   {
    alert("Invalid time. Time shoule be end with AM or PM.");
    return false;
   
}
   }
