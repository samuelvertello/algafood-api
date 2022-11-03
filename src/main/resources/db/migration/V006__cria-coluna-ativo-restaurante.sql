ALTER TABLE restaurante add ativo TINYINT(1) NOT NULL;
UPDATE restaurante set ativo = true;