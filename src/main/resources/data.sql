INSERT INTO user (`user_id`, `password`, `name`, `email`) VALUES ('asdf', 'asdf', 'asdf', 'asdf');
INSERT INTO article (`id`, `title`, `writer`, `writer_id`) VALUES ('1', 'asdf', 'asdf', '1');
INSERT INTO article_content (`id`, `body`, `article_id`) VALUES ('1', 'asdf', '1');
INSERT INTO reply (`content`, `article_id`, `writer_id`, `status`) VALUES ('asdfq', '1', '1', true);
