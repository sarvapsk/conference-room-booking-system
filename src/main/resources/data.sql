INSERT INTO room_details (id, name, max_capacity, room_type, is_active, available_from, available_to)
VALUES (1, 'Amaze', 3, 'CONFERENCE_ROOM', 1, '09:00', '17:30');

INSERT INTO room_details (id, name, max_capacity, room_type, is_active, available_from, available_to)
VALUES (2, 'Beauty', 7, 'CONFERENCE_ROOM', 1, '09:00', '17:30');

INSERT INTO room_details (id, name, max_capacity, room_type, is_active, available_from, available_to)
VALUES (3, 'Inspire', 12, 'CONFERENCE_ROOM', 1, '09:00', '17:30');

INSERT INTO room_details (id, name, max_capacity, room_type, is_active, available_from, available_to)
VALUES (4, 'Strive', 20, 'CONFERENCE_ROOM', 1, '09:00', '17:30');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (1, 1, '09:00', '09:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (2, 1, '13:00', '13:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (3, 1, '17:00', '17:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (4, 2, '09:00', '09:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (5, 2, '13:00', '13:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (6, 2, '17:00', '17:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (7, 3, '09:00', '09:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (8, 3, '13:00', '13:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (9, 3, '17:00', '17:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (10, 4, '09:00', '09:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (11, 4, '13:00', '13:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (12, 4, '17:00', '17:15');