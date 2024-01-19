INSERT INTO room_details (id, name, max_capacity, room_type, is_active, available_from, available_to)
VALUES (100, 'Amaze', 3, 'CONFERENCE_ROOM', 1, '09:00', '17:30');

INSERT INTO room_details (id, name, max_capacity, room_type, is_active, available_from, available_to)
VALUES (101, 'Beauty', 7, 'CONFERENCE_ROOM', 1, '09:00', '17:30');

INSERT INTO room_details (id, name, max_capacity, room_type, is_active, available_from, available_to)
VALUES (102, 'Inspire', 12, 'CONFERENCE_ROOM', 1, '09:00', '17:30');

INSERT INTO room_details (id, name, max_capacity, room_type, is_active, available_from, available_to)
VALUES (103, 'Strive', 20, 'CONFERENCE_ROOM', 1, '09:00', '17:30');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (100, 100, '09:00', '09:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (101, 100, '13:00', '13:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (102, 100, '17:00', '17:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (103, 101, '09:00', '09:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (104, 101, '13:00', '13:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (105, 101, '17:00', '17:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (106, 102, '09:00', '09:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (107, 102, '13:00', '13:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (108, 102, '17:00', '17:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (109, 103, '09:00', '09:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (110, 103, '13:00', '13:15');

INSERT INTO room_maintanence_details (id, fk_room_id, start_time, end_time)
VALUES (111, 103, '17:00', '17:15');