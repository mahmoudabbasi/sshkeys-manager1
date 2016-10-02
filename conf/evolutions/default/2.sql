# --- Sample dataset

# --- !Ups

insert into servers(id, name, comment, status, port, authorized_keys_path) values (
  1,
  'app1.acceptance.elogistics.net',
  'UPS applications acceptance system',
  0,
  22,
  '~/.ssh/authorized_keys');

insert into servers(id, name, comment, status, port, authorized_keys_path) values (
    2,
    'files.acceptance.elogistics.net',
    'UPS file storage acceptance system',
    0,
    22,
    '~/.ssh/authorized_keys');

insert into servers(id, name, comment, status, port, authorized_keys_path) values (
    3,
    'messaging.acceptance.elogistics.net',
    'UPS messaging acceptance system',
    0,
    22,
    '~/.ssh/authorized_keys');

insert into servers(id, name, comment, status, port, authorized_keys_path) values (
    4,
    'app1.elogistics.net',
    'UPS messaging production system',
    0,
    22,
    '~/.ssh/authorized_keys');

insert into servers(id, name, comment, status, port, authorized_keys_path) values (
    5,
    'kate-node1.elogistics.net',
    'UPS kate application node1 production system',
    0,
    22,
    '~/.ssh/authorized_keys');

insert into servers(id, name, comment, status, port, authorized_keys_path) values (
    6,
    'kate-node2.elogistics.net',
    'UPS kate application node2 production system',
    0,
    22,
    '~/.ssh/authorized_keys');

# --- !Downs

delete from servers;
