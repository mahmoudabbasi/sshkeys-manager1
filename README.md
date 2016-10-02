#Description

We need a solution to add or remove access to one server or a group of servers.
We need a solution to manage all the ssh public keys over the servers. Currently we have one user with one or more ssh public keys.

#Functional specification

We have different group of ssh keys:

* Lunatech employees
* Client employees
* External system accessing sFTP to get or put files to servers

Currently the Lunatech's and Client's employees have access to all systems. Some employees can become root.

On the external system, only the sftp access is used (though ssh is enabled and I think the shell is set to /usr/bin/false).

There are x different users:

* Client1
* Client2
* Client3
* ...

Those users have different public keys per environment. So basically the users are fixed here. Only the ssh public keys matter.

Regarding the Lunatech's and client's employees, currently we all have a user account. However, I don't think this is needed. It was used thinking of audit, but quite frankly, I have never seen it in action.

Therefore, to simplify our task we can introduce 3 fixed users across all the servers:

lunatech
lunatech-admin
client

lunatech is a user that is part of the unix lunatech. lunatech-admin is part of lunatech and sudo. With lunatech-admin you can become sudo without password.

Now that we have our two users defined, we need an easy way to manage ssh-keys for all servers. The most sensible way to do that is to have a default user that is allowed to install ssh keys. The best is to have the one that are part of the amazon AWS with the amazon key you start your instance with. So ubuntu and the ups ssh key that is currently in use in amazon.

To setup the users and the sshkey we need a root user (the one you start your instance with ie ubuntu on AWS with the master ssh key)

This is issue is to prepare a transition plan and to execute it. The plan should be flawless, therefore the technical specification need to be completed first.

# Technical specification

# Roles and permissions

See functional specification though it will be good to detail it here.
