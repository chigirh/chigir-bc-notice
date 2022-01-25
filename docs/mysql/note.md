## Settings
### mysql
username:app
password:secret
description:

### testing mysql
username:app
password:secret
description:it is for "jUnit Test", definition is the same as mysql

## Definition
### tables
```sql
+---------------------------+
| Tables_in_bc-notice       |
+---------------------------+
| demo                      |
| notification_mail_address |
+---------------------------+
```

#### demo
description:it is demo
```sql
CREATE TABLE `demo` (
  `key` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `value` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
```

#### notification_mail_address
description:Register a email addresses to notify bitcoin fluctuations.

```sql
CREATE TABLE `notification_mail_address` (
  `mail_address` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `begin_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`mail_address`,`begin_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
```