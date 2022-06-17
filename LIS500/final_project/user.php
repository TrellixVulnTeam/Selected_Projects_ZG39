<html>
    <head>
        <?php
            /*store the sent information in variables*/
            $firstname = $_POST['name'];
            $lastname = $_POST['surname'];
            $email = $_POST['email'];
            $date = $_POST['date'];
            $message = $_POST['message'];

            $title="Registration Receipt of $firstname $lastname ";
            echo "<title>$title</title>";?>
        <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous"/>
    </head> 
    <body>
        <?php echo "<h1> Thank you, $firstname $lastname, for registrating our event! </h1>";?>
        <?php echo "You have choose our event on $date. <br>"?>
    </body>
</html>

<?php 
// handling sending plain text email 
$to = $email;
$subject = $title;
$message = 
"
Welcome, $firstname $lastname!

You have choose: $date to attend the our event.


We hope to meet you on the event!

Regards,

Xizheng Yu, Kaiwen Shi, Joonbo Shim
CSSCE Team
";
$from = 'kshi42@wisc.edu';
 
// Sending email confirmation
if(mail($to, $subject, $message)){
    echo 'The confirmation email with same content has been sent successfully.';
} else{
    echo 'Unable to send confirmation email. Please try again.';
}

echo '<br><a href="./index.html">Click this link to the main page.</a> <br>'

?>