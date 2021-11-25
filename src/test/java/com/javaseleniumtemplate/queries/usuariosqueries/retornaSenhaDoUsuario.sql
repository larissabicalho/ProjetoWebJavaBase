select MD5(password)
  from bugtracker.mantis_user_table
 where username = '$usuario'