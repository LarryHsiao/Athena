CREATE TABLE IF NOT EXISTS vocabulary (
  id    INTEGER PRIMARY KEY AUTOINCREMENT,
  value TEXT NOT NULL,
  note  TEXT NOT NULL
);

-- Use case : Add new vocabulary
INSERT INTO vocabulary (value, note) VALUES ('word', 'note');

-- Use case : Show all vocabulary
SELECT *
FROM vocabulary;

-- Use case : Delete vocabulary
DELETE FROM vocabulary
WHERE id = 1;

-- Use case : Update vocabulary
UPDATE vocabulary
SET value = 'newWord', note = 'newNote'
WHERE id = 1;

