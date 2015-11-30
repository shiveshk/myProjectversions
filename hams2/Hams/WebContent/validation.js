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
   
}
