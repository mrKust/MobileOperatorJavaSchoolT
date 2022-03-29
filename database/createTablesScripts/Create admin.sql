use db_ecare;

insert into client (
  first_name,
  surname,
  email_address,
  password_log_in,
  user_role
) values ('admin', 'superadmin', 'admin', '$2a$12$7L7CDpfHBXTPci8U0MNQs.Pu9fZbherdcYKuS5SC05JW1MV4gD32y', 'control');