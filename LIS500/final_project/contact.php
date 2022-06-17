<html>
    <head>
        <?php
            /*store the sent information in variables*/
            $firstname = $_POST['firstname'];
            $lastname = $_POST['lastname'];
            $pronouns = $_POST['pronouns'];
            $email = $_POST['email'];
        
            $title="$lastname, $firstname";
            echo "<title>$title</title>";
        ?>
    </head> 
    <body>
        <?php echo "<h1> Welcome, $pronouns $title! </h1>";?>
        <?php echo "You have Registered to our name list"?>
        <?php echo "<br>" ?>
        <?php echo "We hope to talk to you soon! A confirmation message has been sent to your email"?>
        <?php echo "<br>" ?>
        
    </body>
</html>

<?php 
// handling sending plain text email 
$to = $email;
$subject = "CSSCE name list registration";
$message = 
"
Welcome, $pronouns $title!

You have signed up on our name list. We will send any related information to you.

We hope to meet you soon!

Regards,

Shi Kaiwen, Yu Xizheng, Joonbo Shim
CSSCE
";
$from = 'kshi42@wisc.edu';
 
// Sending email confirmation
if(mail($to, $subject, $message)){
    echo 'The confirmation email with same content has been sent successfully.';
} else{
    echo 'Unable to send confirmation email. Please try again.';
}

?>


